package com.totvs.sl.school.core.aluno.api;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(description = "Informações para cadastro de um novo aluno.")
public class CriarAlunoDto {

	private String nome;

	private String cpf;

	private String email;

	private String formaIngresso;

	private int matricula;
}
