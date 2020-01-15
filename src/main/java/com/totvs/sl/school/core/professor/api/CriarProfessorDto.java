package com.totvs.sl.school.core.professor.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CriarProfessorDto {

	@ApiModelProperty(value = "Nome do professor.", required = true)
	@NotBlank(message = "{CriarProfessorDTO.nome.NotBlank}")
	@Size(max = 60, message = "{CriarProfessorDTO.nome.Size}")
	private String nome;

	@ApiModelProperty(value = "Documento CPF do professor.", required = true)
	@NotBlank(message = "{CriarProfessorDTO.cpf.NotBlank}")
	@Size(min = 11, max = 14, message = "{CriarProfessorDTO.cpf.Size}")
	private String cpf;

	@ApiModelProperty(value = "E-mail do professor.", required = true)
	@NotBlank(message = "{CriarProfessorDTO.email.NotBlank}")
	@Size(max = 60, message = "{CriarProfessorDTO.email.Size}")
	private String email;

	@ApiModelProperty(value = "Titulação do professor.", required = true)
	@NotBlank(message = "{CriarProfessorDTO.titulacao.NotBlank}")
	@Size(max = 60, message = "{CriarProfessorDTO.titulacao.Size}")
	private String titulacao;
}
