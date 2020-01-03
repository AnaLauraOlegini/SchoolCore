package com.totvs.sl.school.core.disciplina.exception;

import java.util.List;

import com.totvs.tjf.api.context.stereotype.ApiErrorParameter;

public class SchoolIdDisciplinaDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = -9014062967580598305L;

	@ApiErrorParameter
	private final List<String> id;

	public SchoolIdDisciplinaDuplicadoException(List<String> id) {
		this.id = id;
	}
}