package com.totvs.sl.school.core.turma.domain.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.totvs.sl.school.core.turma.domain.model.TurmaId;

public class TurmaIdTest {
    
    @Test
    @Description("Gerar o código UUID da Turma.")
    public void deveGerarCodigoUUID() {
        TurmaId id = TurmaId.generate();
        assertNotNull(id);
    }

    @Test
    @Description("Deve retornar o código UUID da Turma - Tipo String.")
    public void deveRetornarCodigoUUIDTipoString() {
        TurmaId id = TurmaId.generate();

        assertTrue(id.toString() instanceof String);
    }
}
