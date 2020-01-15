package com.totvs.sl.school.core.disciplina.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.totvs.sl.school.core.disciplina.domain.enums.Disciplinas;
import com.totvs.sl.school.core.professor.domain.model.ProfessorId;
import com.totvs.tjf.core.stereotype.Aggregate;
import com.totvs.tjf.core.stereotype.AggregateIdentifier;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Aggregate
@NoArgsConstructor
public class Disciplina {

	@AggregateIdentifier
	private DisciplinaId id;

	private String descricao;

	private Disciplinas sigla;

	private int cargaHoraria;

	private List<ProfessorId> professorId = new ArrayList<>();

	@Builder
	public Disciplina(DisciplinaId id,
	                  String descricao,
	                  Disciplinas sigla,
	                  int cargaHoraria,
	                  List<ProfessorId> professorId) {
		this.id = id;
		this.descricao = descricao;
		this.sigla = sigla;
		this.cargaHoraria = cargaHoraria;
		this.professorId = professorId;
	}

}
