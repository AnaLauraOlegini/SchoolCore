package com.totvs.sl.school.core.aluno.exception;

import com.totvs.tjf.api.context.stereotype.ApiErrorParameter;
import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

import lombok.Getter;

@ApiBadRequest("SchoolCpfDoAlunoDuplicadoException")
@Getter
public class SchoolCpfDoAlunoDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = -9014062967580598305L;

	@ApiErrorParameter
	private String cpf;

	public SchoolCpfDoAlunoDuplicadoException(String cpf) {
		this.cpf = cpf;
	}

}
