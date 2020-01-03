package com.totvs.sl.school.core.turma.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaId;
import com.totvs.tjf.core.stereotype.Aggregate;
import com.totvs.tjf.core.stereotype.AggregateIdentifier;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Aggregate
@NoArgsConstructor
public class Turma {

	@NotNull
	@AggregateIdentifier
	private TurmaId id;

	@NotNull
	private String descricao;

	private int anoLetivo;

	private int periodoLetivo;

	private int numeroVagas;

	private List<AlunoId> alunoId = new ArrayList<>();

	private List<DisciplinaId> disciplinaId = new ArrayList<>();

	@Builder
	public Turma(TurmaId id,
	             String descricao,
	             int anoLetivo,
	             int periodoLetivo,
	             int numeroVagas,
	             List<AlunoId> alunoId,
	             List<DisciplinaId> disciplinaId) {
		this.id = id;
		this.descricao = descricao;
		this.anoLetivo = anoLetivo;
		this.periodoLetivo = periodoLetivo;
		this.numeroVagas = numeroVagas;
		this.disciplinaId = disciplinaId;
		this.alunoId = alunoId;
	}

}
