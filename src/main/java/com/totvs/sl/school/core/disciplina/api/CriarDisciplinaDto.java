package com.totvs.sl.school.core.disciplina.api;

import java.util.List;

import com.totvs.sl.school.core.disciplina.domain.enums.Disciplinas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CriarDisciplinaDto {

	private String descricao;

	private Disciplinas sigla;

	private int cargaHoraria;

	private List<String> professorId;

}
