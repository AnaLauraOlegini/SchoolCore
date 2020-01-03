package com.totvs.sl.school.core.turma.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

@ApiBadRequest("SchoolCriarTurmaException")
public class SchoolCriarTurmaException extends ConstraintViolationException {

	private static final long serialVersionUID = 7686045486654120992L;

	public SchoolCriarTurmaException(Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}

}
