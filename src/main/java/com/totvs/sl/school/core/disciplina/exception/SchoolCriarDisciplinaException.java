package com.totvs.sl.school.core.disciplina.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

@ApiBadRequest("SchoolCriarDisciplinaException")
public class SchoolCriarDisciplinaException extends ConstraintViolationException {

	private static final long serialVersionUID = 7686045486654120992L;

	public SchoolCriarDisciplinaException(Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}

}
