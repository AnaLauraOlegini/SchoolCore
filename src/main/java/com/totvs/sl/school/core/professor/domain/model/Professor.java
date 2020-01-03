package com.totvs.sl.school.core.professor.domain.model;

import javax.validation.constraints.NotNull;

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
	@NotNull
	private String nome;
	@NotNull
	private CPF cpf;
	@NotNull
	private String email;
	@NotNull
	private String titulacao;

	@Builder
	private Professor(ProfessorId id, String nome, CPF cpf, String email, String titulacao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.titulacao = titulacao;

	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public CPF getCpf() {
		return this.cpf;
	}

	@Override
	public String getEmail() {
		return this.email;
	}
}
