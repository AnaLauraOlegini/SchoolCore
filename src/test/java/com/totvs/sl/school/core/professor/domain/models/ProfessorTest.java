package com.totvs.sl.school.core.professor.domain.models;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.sl.school.core.professor.domain.model.Professor;

public class ProfessorTest {

	private Professor newProfessor() {
		return Professor.builder()
		                .cpf(CPF.of("13886480917"))
		                .nome("Augusto Roberto Moura")
		                .email("aaugustorobertomoura@2emesconstrutora.com.br")
		                .titulacao("Mestre")
		                .build();
	}

	@Test
	@Description("Incluir um professor.")
	public void professorDeveSerIncluido() {
		Professor professor = this.newProfessor();
		assertNotEquals(null, professor);
	}
}
