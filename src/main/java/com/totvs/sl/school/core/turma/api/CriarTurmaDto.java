package com.totvs.sl.school.core.turma.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CriarTurmaDto {

	@ApiModelProperty(value = "Descrição da turma.", required = true)
	@NotBlank(message = "{CriarTurmaDto.descricao.NotBlank}")
	@Size(max = 60, message = "{CriarTurmaDto.descricao.Size}")
	private String descricao;

	@ApiModelProperty(value = "Ano Letivo da turma.", required = true)
	private int anoLetivo;

	@ApiModelProperty(value = "Período Letivo da turma.", required = true)
	private int periodoLetivo;

	@ApiModelProperty(value = "Número de vagas da turma.", required = true)
	private int numeroVagas;

	@ApiModelProperty(value = "Disciplinas da turma.", required = true)
	@NotBlank(message = "{CriarTurmaDto.disciplinaId.NotBlank}")
	@Valid
	private List<String> disciplinaId;

	@ApiModelProperty(value = "Alunos da turma.", required = true)
	@NotBlank(message = "{CriarTurmaDto.alunoId.NotBlank}")
	@Valid
	private List<String> alunoId;

}
