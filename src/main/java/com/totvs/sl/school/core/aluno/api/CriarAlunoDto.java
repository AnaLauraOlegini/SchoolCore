package com.totvs.sl.school.core.aluno.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CriarAlunoDto {

	@ApiModelProperty(value = "Nome do aluno.", required = true)
	@NotBlank(message = "{CriarAlunoDTO.nome.NotBlank}")
	@Size(max = 60, message = "{CriarAlunoDTO.nome.Size}")
	private String nome;

	@ApiModelProperty(value = "Documento CPF do aluno.", required = true)
	@NotBlank(message = "{CriarAlunoDTO.cpf.NotBlank}")
	@Size(min = 11, max = 14, message = "{CriarAlunoDTO.cpf.Size}")
	private String cpf;

	@ApiModelProperty(value = "E-mail do aluno.", required = true)
	@NotBlank(message = "{CriarAlunoDTO.email.NotBlank}")
	@Size(max = 60, message = "{CriarAlunoDTO.email.Size}")
	private String email;

	@ApiModelProperty(value = "Forma ingresso do aluno.", required = true)
	@NotBlank(message = "{CriarAlunoDTO.ingresso.NotBlank}")
	@Size(max = 60, message = "{CriarAlunoDTO.ingresso.Size}")
	private String formaIngresso;

	@ApiModelProperty(value = "Matricula do aluno.", required = true)
	private int matricula;
}
