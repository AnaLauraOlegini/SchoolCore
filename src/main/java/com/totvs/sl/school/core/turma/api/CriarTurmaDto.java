package com.totvs.sl.school.core.turma.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CriarTurmaDto {

	private String descricao;

	private int anoLetivo;

	private int periodoLetivo;

	private int numeroVagas;

	private List<String> disciplinaId;

	private List<String> alunoId;

}
