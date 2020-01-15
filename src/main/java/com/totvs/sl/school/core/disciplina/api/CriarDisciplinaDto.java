package com.totvs.sl.school.core.disciplina.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.totvs.sl.school.core.disciplina.domain.enums.Disciplinas;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarDisciplinaDto {

	@ApiModelProperty(value = "Descrição da disciplina.", required = true)
	@NotBlank(message = "{CriarDisciplinaDto.descricao.NotBlank}")
	@Size(max = 60, message = "{CriarDisciplinaDto.descricao.Size}")
	private String descricao;

	@ApiModelProperty(value = "Sigla da disciplina.", required = true)
	@NotBlank(message = "{CriarDisciplinaDto.sigla.NotBlank}")
	private Disciplinas sigla;

	@ApiModelProperty(value = "Carga horária da disciplina.", required = true)
	private int cargaHoraria;

	@ApiModelProperty(value = "Professores da disciplina.", required = true)
	@NotBlank(message = "{CriarDisciplinaDto.professorId.NotBlank}")
	@Valid
	private List<String> professorId;

}
