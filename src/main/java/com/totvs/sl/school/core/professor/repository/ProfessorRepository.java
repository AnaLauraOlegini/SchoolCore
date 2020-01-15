package com.totvs.sl.school.core.professor.repository;

import java.sql.Types;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totvs.sl.school.core.professor.domain.model.Professor;
import com.totvs.sl.school.core.professor.domain.model.ProfessorDomainRepository;
import com.totvs.tjf.repository.aggregate.CrudAggregateRepository;

@Repository
public class ProfessorRepository extends CrudAggregateRepository<Professor, String>
        implements ProfessorDomainRepository {

	public ProfessorRepository(EntityManager em, ObjectMapper mapper) {
		super(em, mapper.copy());
	}
	
	@Override
	protected String getTableName() {
		return "professor";
	}

	@Override
	public boolean cpfDoProfessorExiste(String cpf) {
		return this.exists("data->'cpf'->>'numero' = ?", new SqlParameterValue(Types.VARCHAR, cpf));
	}

	@Override
	public Optional<Professor> getByCpf(String cpf) {
		return this.findOne("data->'cpf'->>'numero' = ?", new SqlParameterValue(Types.VARCHAR, cpf));
	}

}
