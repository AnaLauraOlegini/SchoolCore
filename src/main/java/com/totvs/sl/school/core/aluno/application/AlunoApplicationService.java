package com.totvs.sl.school.core.aluno.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totvs.sl.school.core.aluno.amqp.events.AlunoCriadoEvent;
import com.totvs.sl.school.core.aluno.domain.model.Aluno;
import com.totvs.sl.school.core.aluno.domain.model.AlunoDomainRepository;
import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.aluno.exception.SchoolCpfDoAlunoDuplicadoException;
import com.totvs.sl.school.core.amqp.SchoolPublisher;

@Service
@Transactional
public class AlunoApplicationService {

	@Autowired
	private AlunoDomainRepository alunoRepository;

	@Autowired
	private SchoolPublisher schoolPublisher;

	public AlunoApplicationService(AlunoDomainRepository alunoRepository, SchoolPublisher schoolPublisher) {
		this.alunoRepository = alunoRepository;
		this.schoolPublisher = schoolPublisher;
	}

	public AlunoId handle(CriarAlunoCommand cmd) {

		Aluno aluno = Aluno.builder()
		                   .id(AlunoId.generate())
		                   .nome(cmd.getNome())
		                   .cpf(cmd.getCpf())
		                   .email(cmd.getEmail())
		                   .formaIngresso(cmd.getFormaIngresso())
		                   .matricula(cmd.getMatricula())
		                   .build();

		if (this.alunoRepository.cpfDoAlunoExiste(cmd.getCpf().getNumero()))
			throw new SchoolCpfDoAlunoDuplicadoException(cmd.getCpf().getNumero());

		this.alunoRepository.insert(aluno);

		schoolPublisher.publish(AlunoCriadoEvent.builder()
		                                        .alunoId(aluno.getId().toString())
		                                        .nome(aluno.getNome())
		                                        .cpf(aluno.getCpf().getNumero())
		                                        .email(aluno.getEmail())
		                                        .formaIngresso(aluno.getFormaIngresso())
		                                        .matricula(aluno.getMatricula())
		                                        .build());

		return aluno.getId();
	}
}
