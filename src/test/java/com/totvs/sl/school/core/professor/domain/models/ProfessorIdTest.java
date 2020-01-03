package com.totvs.sl.school.core.professor.domain.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.totvs.sl.school.core.professor.domain.model.ProfessorId;

public class ProfessorIdTest {

	@Test
	@Description("Gerar o código UUID do professor.")
	public void deveGerarCodigoUUID() {
		ProfessorId id = ProfessorId.generate();
		assertNotNull(id);
	}

	@Test
	@Description("Deve retornar o código UUID do professor - Tipo String.")
	public void deveRetornarCodigoUUIDTipoString() {
		ProfessorId id = ProfessorId.generate();

		assertTrue(id.toString() instanceof String);
	}
}
