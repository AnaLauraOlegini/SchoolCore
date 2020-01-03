package com.totvs.sl.school.core.professor.exception;

import com.totvs.tjf.api.context.stereotype.ApiErrorParameter;

public class SchoolCpfDoProfessorDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = -9014062967580598305L;

	@ApiErrorParameter
	private String cpf;

	public SchoolCpfDoProfessorDuplicadoException(String cpf) {
		this.cpf = cpf;
	}
}
