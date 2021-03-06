package com.totvs.sl.school.core.professor.domain.model;

import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.sl.school.core.pessoa.domain.Pessoa;
import com.totvs.tjf.core.stereotype.Aggregate;
import com.totvs.tjf.core.stereotype.AggregateIdentifier;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Aggregate
@NoArgsConstructor
public class Professor extends Pessoa {

	@AggregateIdentifier
	private ProfessorId id;

	private String titulacao;

	@Builder
	private Professor(ProfessorId id, String nome, CPF cpf, String email, String titulacao) {
		this.id = id;
		super.nome = nome;
		super.cpf = cpf;
		super.email = email;
		this.titulacao = titulacao;

	}
}
