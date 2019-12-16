package com.totvs.sl.school.core.turma.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.totvs.sl.school.core.turma.domain.model.Turma;
import com.totvs.sl.school.core.turma.domain.model.TurmaDomainRepository;
import com.totvs.tjf.repository.aggregate.CrudAggregateRepository;

@Repository
public class TurmaRepository extends CrudAggregateRepository<Turma, String> implements TurmaDomainRepository{

        public TurmaRepository(EntityManager em, ObjectMapper mapper) {
            super(em, mapper.copy());
        }

        protected String getTableName() {
            return "turma";
        }
            
    
}
