package com.totvs.sl.school.core.aluno.repository;

import java.sql.Types;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totvs.sl.school.core.aluno.domain.model.Aluno;
import com.totvs.sl.school.core.aluno.domain.model.AlunoDomainRepository;
import com.totvs.tjf.repository.aggregate.CrudAggregateRepository;

@Repository
public class AlunoRepository extends CrudAggregateRepository<Aluno, String> implements AlunoDomainRepository {

    public AlunoRepository(EntityManager em, ObjectMapper mapper) {
        super(em, mapper.copy());
    }

    protected String getTableName() {
        return "aluno";
    }

    @Override
    public boolean cpfDoAlunoExiste(String cpf) {
        return this.exists("data->'cpf'->>'numero' = ?", new SqlParameterValue(Types.VARCHAR, cpf));
    }

    @Override
    public Optional<Aluno> getByCpf(String cpf) {
        return this.findOne("data->'cpf'->>'numero' = ?", new SqlParameterValue(Types.VARCHAR, cpf));
    }
}
