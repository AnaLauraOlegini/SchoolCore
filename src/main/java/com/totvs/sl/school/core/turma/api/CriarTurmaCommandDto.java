package com.totvs.sl.school.core.turma.api;

import java.util.List;

import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CriarTurmaCommandDto {

	private String descricao;

	private int anoLetivo;

	private int periodoLetivo;

	private int numeroVagas;

	private List<DisciplinaId> disciplinaId;

	private List<AlunoId> alunoId;

}
