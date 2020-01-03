package com.totvs.sl.school.core.disciplina.domain.model;

import java.util.List;

import com.totvs.tjf.repository.aggregate.AggregateRepository;

public interface DisciplinaDomainRepository extends AggregateRepository<Disciplina, String> {

	boolean verificaSeProfessorJaExisteNaDisciplina(List<String> professorId);

}
