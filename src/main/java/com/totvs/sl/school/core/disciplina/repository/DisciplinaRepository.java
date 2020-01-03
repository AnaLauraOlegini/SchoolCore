package com.totvs.sl.school.core.disciplina.repository;

import java.sql.Types;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.jdbc.core.SqlParameterValue;
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

	@Override
	protected String getTableName() {
		return "disciplina";
	}

	@Override
	public boolean verificaSeProfessorJaExisteNaDisciplina(List<String> professorId) {
		// return this.exists("data->'professorId' @> ?", new
		// SqlParameterValue(Types.OTHER, "[{\"professorId\":{\"id\":\"" + professorId +
		// "\"}}]"));
		return this.exists("data->'professorId' = ?",
		                   new SqlParameterValue(Types.OTHER, "[{\"professorId\":{\"id\":\"" + professorId + "\"}}]"));
	}

}
