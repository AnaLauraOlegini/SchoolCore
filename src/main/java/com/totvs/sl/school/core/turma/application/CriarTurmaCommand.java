package com.totvs.sl.school.core.turma.application;

import java.util.List;

import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaId;

import lombok.Data;
import lombok.Getter;

@Getter
@Data(staticConstructor = "of")
public class CriarTurmaCommand {

	private final String descricao;

	private final int anoLetivo;

	private final int periodoLetivo;

	private final int numeroVagas;

	private final List<DisciplinaId> disciplinaId;

	private final List<AlunoId> alunoId;

}
