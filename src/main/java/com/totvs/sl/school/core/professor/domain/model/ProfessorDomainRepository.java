package com.totvs.sl.school.core.professor.domain.model;

import java.util.Optional;

import com.totvs.tjf.repository.aggregate.AggregateRepository;

public interface ProfessorDomainRepository extends AggregateRepository<Professor, String> {

	boolean cpfDoProfessorExiste(String cpf);

	Optional<Professor> getByCpf(String cpf);

}
