package com.totvs.sl.school.core.disciplina.domain.model;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.totvs.sl.school.core.Fabrica;

public class DisciplinaTest {
    
    private Disciplina newDisciplina() {
        return Disciplina.builder()
                .id(DisciplinaId.generate())
                .descricao("Português")
                .sigla("POR")
                .cargaHoraria(4)
                .professorId(Fabrica.umProfessorParaUmaDisciplina())
                .build();
    }

    @Test
    @Description("Incluir uma Disciplina.")
    public void disciplinaDeveSerIncluido() {
        Disciplina disciplina = this.newDisciplina();
        assertNotEquals(null, disciplina);
    }
}
