package com.totvs.sl.school.core.aluno.domain.model;

import java.util.Optional;

import com.totvs.tjf.repository.aggregate.AggregateRepository;

public interface AlunoDomainRepository extends AggregateRepository<Aluno, String> {
	
    boolean cpfDoAlunoExiste(String cpf);
    Optional<Aluno> getByCpf(String cpf);
}