package com.totvs.sl.school.core.aluno.application;

import com.totvs.sl.school.core.pessoa.domain.CPF;

import lombok.Data;
import lombok.Getter;

@Getter
@Data(staticConstructor = "of")
public final class CriarAlunoCommand {

    private final String nome;
    private final CPF cpf;
    private final String email;
    private final String formaIngresso;
    private final int matricula;
    
}
