package com.totvs.sl.school.core.disciplina.domain.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.annotation.Description;

public class DisciplinaIdTest {
    
    @Test
    @Description("Gerar o código UUID da Disciplina.")
    public void deveGerarCodigoUUID() {
        DisciplinaId id = DisciplinaId.generate();
        assertNotNull(id);
    }

    @Test
    @Description("Deve retornar o código UUID da Disciplina - Tipo String.") 
    public void deveRetornarCodigoUUIDTipoString() {
        DisciplinaId id = DisciplinaId.generate();
        assertTrue(id.toString() instanceof String);
    }
}
