package com.totvs.sl.school.core.disciplina.api;

import java.util.List;

import com.totvs.sl.school.core.professor.domain.model.ProfessorId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CriarDisciplinaCommandDto {
    
    private String descricao;
    
    private String sigla;
    
    private int cargaHoraria;
    
    private List<ProfessorId> professorId;
}
