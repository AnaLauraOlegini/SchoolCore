package com.totvs.sl.school.core.turma.application;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.totvs.sl.school.core.amqp.SchoolPublisher;
import com.totvs.sl.school.core.turma.amqp.events.TurmaCriadaEvent;
import com.totvs.sl.school.core.turma.domain.model.Turma;
import com.totvs.sl.school.core.turma.domain.model.TurmaDomainRepository;
import com.totvs.sl.school.core.turma.domain.model.TurmaId;

@Service
@Transactional
public class TurmaApplicationService {

	@Autowired
	SchoolPublisher schoolPublisher;

	@Autowired
	TurmaDomainRepository turmaDomainRepository;

	public TurmaId handle(final CriarTurmaCommand cmd) {

		Turma turma = Turma.builder()
		                   .id(TurmaId.generate())
		                   .descricao(cmd.getDescricao())
		                   .anoLetivo(cmd.getAnoLetivo())
		                   .periodoLetivo(cmd.getPeriodoLetivo())
		                   .numeroVagas(cmd.getNumeroVagas())
		                   .disciplinaId(cmd.getDisciplinaId())
		                   .alunoId(cmd.getAlunoId())
		                   .build();

		this.turmaDomainRepository.insert(turma);

		schoolPublisher.publish(TurmaCriadaEvent.builder()
		                                        .turmaId(turma.getId().toString())
		                                        .descricao(turma.getDescricao())
		                                        .anoLetivo(turma.getAnoLetivo())
		                                        .periodoLetivo(turma.getPeriodoLetivo())
		                                        .numeroVagas(turma.getNumeroVagas())
		                                        .disciplinaId(turma.getDisciplinaId()
		                                                           .stream()
		                                                           .map(String::valueOf)
		                                                           .collect(Collectors.toList()))
		                                        .alunoId(turma.getAlunoId()
		                                                      .stream()
		                                                      .map(String::valueOf)
		                                                      .collect(Collectors.toList()))
		                                        .build());

		return turma.getId();

	}
}
