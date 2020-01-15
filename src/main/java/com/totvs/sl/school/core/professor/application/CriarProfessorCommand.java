package com.totvs.sl.school.core.professor.application;

import com.totvs.sl.school.core.pessoa.domain.CPF;

import lombok.Data;
import lombok.Getter;

@Getter
@Data(staticConstructor = "of")
public final class CriarProfessorCommand {

	private final String nome;

	private final CPF cpf;

	private final String email;

	private final String titulacao;
}
