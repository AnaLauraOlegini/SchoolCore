package com.totvs.sl.school.core.professor.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totvs.sl.school.core.amqp.SchoolPublisher;
import com.totvs.sl.school.core.professor.amqp.events.ProfessorCriadoEvent;
import com.totvs.sl.school.core.professor.domain.model.Professor;
import com.totvs.sl.school.core.professor.domain.model.ProfessorDomainRepository;
import com.totvs.sl.school.core.professor.domain.model.ProfessorId;
import com.totvs.sl.school.core.professor.exception.SchoolCpfDoProfessorDuplicadoException;

@Service
@Transactional
public class ProfessorApplicationService {

	@Autowired
	private ProfessorDomainRepository professorRepository;

	@Autowired
	private SchoolPublisher schoolPublisher;

	public ProfessorId handle(CriarProfessorCommand cmd) {

		Professor professor = Professor.builder()
		                               .id(ProfessorId.generate())
		                               .cpf(cmd.getCpf())
		                               .nome(cmd.getNome())
		                               .email(cmd.getEmail())
		                               .titulacao(cmd.getTitulacao())
		                               .build();

		if (this.professorRepository.cpfDoProfessorExiste(cmd.getCpf().getNumero()))
			throw new SchoolCpfDoProfessorDuplicadoException(cmd.getCpf().getNumero());

		this.professorRepository.insert(professor);

		schoolPublisher.publish(ProfessorCriadoEvent.builder()
		                                            .professorId(professor.getId().toString())
		                                            .nome(professor.getNome())
		                                            .cpf(professor.getCpf().getNumero())
		                                            .email(professor.getEmail())
		                                            .titulacao(professor.getTitulacao())
		                                            .build());

		return professor.getId();

	}
}
