package com.totvs.sl.school.core.aluno.exception;

import com.totvs.tjf.api.context.stereotype.ApiErrorParameter;
import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

import lombok.Getter;

@Getter
@ApiBadRequest("SchoolCpfDoAlunoDuplicadoException")
public class SchoolCpfDoAlunoDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = -9014062967580598305L;

	@ApiErrorParameter
	private final String cpf;

	public SchoolCpfDoAlunoDuplicadoException(String cpf) {
		this.cpf = cpf;
	}

}
