package com.totvs.sl.school.core.aluno.domain.model;

import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.sl.school.core.pessoa.domain.Pessoa;
import com.totvs.tjf.core.stereotype.Aggregate;
import com.totvs.tjf.core.stereotype.AggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Pessoa {

	@AggregateIdentifier
	private AlunoId id;

	private String formaIngresso;

	private int matricula;

	@Builder
	private Aluno(AlunoId id, String nome, CPF cpf, String email, int matricula, String formaIngresso) {
		this.id = id;
		super.nome = nome;
		super.cpf = cpf;
		super.email = email;
		this.matricula = matricula;
		this.formaIngresso = formaIngresso;
	}

}
