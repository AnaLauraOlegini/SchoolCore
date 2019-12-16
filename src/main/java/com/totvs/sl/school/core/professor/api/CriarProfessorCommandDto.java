package com.totvs.sl.school.core.professor.api;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(description = "Informações para cadastro de um novo professor.")
public class CriarProfessorCommandDto {

    private String nome;

    private String cpf;

    private String email;

    private String titulacao;
}
