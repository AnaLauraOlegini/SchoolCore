package com.totvs.sl.school.core.aluno.domain.model;

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
public class Aluno extends Pessoa {

	@NotNull
	@AggregateIdentifier
	private AlunoId id;

	@NotNull
	private String formaIngresso;
	@NotNull
	private int matricula;

	@Builder
	private Aluno(AlunoId id, String nome, CPF cpf, String email, int matricula, String formaIngresso) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.matricula = matricula;
		this.formaIngresso = formaIngresso;
	}

	public String getNome() {
		return this.nome;
	}
	
	public CPF getCpf() {
		return this.cpf;
	}
	
	public String getEmail() {
		return this.email;
	}

}
