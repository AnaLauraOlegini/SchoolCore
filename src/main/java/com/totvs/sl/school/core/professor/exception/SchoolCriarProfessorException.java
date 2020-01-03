package com.totvs.sl.school.core.professor.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

@ApiBadRequest("SchoolCriarProfessorException")
public class SchoolCriarProfessorException extends ConstraintViolationException {

	private static final long serialVersionUID = 7686045486654120992L;

	public SchoolCriarProfessorException(Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}
}
