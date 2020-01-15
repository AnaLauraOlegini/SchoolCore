package com.totvs.sl.school.core.pessoa.domain;

import lombok.Getter;

@Getter
public abstract class Pessoa {
	
	protected String nome;
	protected CPF cpf;
	protected String email;
}