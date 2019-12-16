package com.totvs.sl.school.core.aluno.domain.models;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.context.annotation.Description;

import com.totvs.sl.school.core.aluno.domain.model.Aluno;
import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.pessoa.domain.CPF;

public class AlunoTest {

    private Aluno newAluno() {
        return Aluno.builder()
                .id(AlunoId.generate())
                .cpf(CPF.of("85883483909"))
                .email("nnoahvictorsouza@iname.com")
                .nome("Noah Victor Souza")
                .formaIngresso("vestibular")
                .matricula(168148616)
                .build();
    }

    @Test
    @Description("Incluir um aluno.")
    public void alunoDeveSerIncluido() {
        Aluno aluno = this.newAluno();
        assertNotEquals(null, aluno);
    }
    
}
