package com.totvs.sl.school.core.turma.domain.models;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.totvs.sl.school.core.turma.domain.model.Turma;
import com.totvs.sl.school.core.turma.domain.model.TurmaId;

public class TurmaTest {

	private Turma newTurma() {
		return Turma.builder()
		            .id(TurmaId.generate())
		            .descricao("Turma Enade")
		            .anoLetivo(2019)
		            .periodoLetivo(01)
		            .numeroVagas(20)
		            .build();
	}

	@Test
	@Description("Incluir um Turma.")
	public void TurmaDeveSerIncluido() {
		Turma turma = this.newTurma();
		assertNotEquals(null, turma);
	}

}
