package com.totvs.sl.school.core.aluno.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

@ApiBadRequest("SchoolCriarAlunoException")
public class SchoolCriarAlunoException extends ConstraintViolationException {
	
	private static final long serialVersionUID = 7686045486654120992L;

	public SchoolCriarAlunoException(Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}
}

