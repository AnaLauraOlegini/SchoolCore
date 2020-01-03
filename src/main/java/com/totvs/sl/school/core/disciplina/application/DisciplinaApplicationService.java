package com.totvs.sl.school.core.disciplina.application;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totvs.sl.school.core.amqp.SchoolPublisher;
import com.totvs.sl.school.core.disciplina.amqp.events.DisciplinaCriadaEvent;
import com.totvs.sl.school.core.disciplina.domain.model.Disciplina;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaDomainRepository;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaId;
import com.totvs.sl.school.core.disciplina.exception.SchoolIdDisciplinaDuplicadoException;

@Service
@Transactional
public class DisciplinaApplicationService {

	@Autowired
	private DisciplinaDomainRepository disciplinaDomainRepository;

	@Autowired
	SchoolPublisher schoolPublisher;

	public DisciplinaId handle(final CriarDisciplinaCommand cmd) {

		Disciplina disciplina = Disciplina.builder()
		                                  .id(DisciplinaId.generate())
		                                  .descricao(cmd.getDescricao())
		                                  .sigla(cmd.getSigla())
		                                  .cargaHoraria(cmd.getCargaHoraria())
		                                  .professorId(cmd.getProfessorId())
		                                  .build();

		if (this.disciplinaDomainRepository.verificaSeProfessorJaExisteNaDisciplina(cmd.getProfessorId()
		                                                                               .stream()
		                                                                               .map(String::valueOf)
		                                                                               .collect(Collectors.toList())))
			throw new SchoolIdDisciplinaDuplicadoException(cmd.getProfessorId()
			                                                  .stream()
			                                                  .map(String::valueOf)
			                                                  .collect(Collectors.toList()));

		this.disciplinaDomainRepository.insert(disciplina);

		schoolPublisher.publish(DisciplinaCriadaEvent.builder()
		                                             .disciplinaId(disciplina.getId().toString())
		                                             .descricao(disciplina.getDescricao())
		                                             .sigla(disciplina.getSigla())
		                                             .cargaHoraria(disciplina.getCargaHoraria())
		                                             .professorId(disciplina.getProfessorId()
		                                                                    .stream()
		                                                                    .map(String::valueOf)
		                                                                    .collect(Collectors.toList()))
		                                             .build());

		return disciplina.getId();
	}

}
