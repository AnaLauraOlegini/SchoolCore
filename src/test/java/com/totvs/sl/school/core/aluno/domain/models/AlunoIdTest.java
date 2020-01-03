package com.totvs.sl.school.core.aluno.domain.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.totvs.sl.school.core.aluno.domain.model.AlunoId;

public class AlunoIdTest {

	@Test
	@Description("Gerar o código UUID do aluno.")
	public void deveGerarCodigoUUID() {
		AlunoId id = AlunoId.generate();
		assertNotNull(id);
	}

	@Test
	@Description("Deve retornar o código UUID do aluno - Tipo String.")
	public void deveRetornarCodigoUUIDTipoString() {
		AlunoId id = AlunoId.generate();

		assertTrue(id.toString() instanceof String);
	}

}
