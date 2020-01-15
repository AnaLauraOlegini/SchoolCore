package com.totvs.sl.school.core.professor.exception;

import com.totvs.tjf.api.context.stereotype.ApiErrorParameter;
import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

import lombok.Getter;

@Getter
@ApiBadRequest("SchoolCpfDoProfessorDuplicadoException")
public class SchoolCpfDoProfessorDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = -9014062967580598305L;

	@ApiErrorParameter
	private final String cpf;

	public SchoolCpfDoProfessorDuplicadoException(String cpf) {
		this.cpf = cpf;
	}
}
