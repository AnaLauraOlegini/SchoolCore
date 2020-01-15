package com.totvs.sl.school.core.disciplina.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totvs.sl.school.core.disciplina.domain.model.Disciplina;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaDomainRepository;
import com.totvs.tjf.repository.aggregate.CrudAggregateRepository;

@Repository
public class DisciplinaRepository extends CrudAggregateRepository<Disciplina, String>
        implements DisciplinaDomainRepository {

	public DisciplinaRepository(EntityManager em, ObjectMapper mapper) {
		super(em, mapper.copy());
	}

	protected String getTableName() {
		return "disciplina";
	}

}
