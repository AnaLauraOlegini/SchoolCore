package com.totvs.sl.school.core.disciplina.application;

import java.util.List;

import com.totvs.sl.school.core.professor.domain.model.ProfessorId;

import lombok.Data;
import lombok.Getter;

@Getter
@Data(staticConstructor = "of")
public final class CriarDisciplinaCommand {
    
    private final String descricao;
    
    private final String sigla;
    
    private final int cargaHoraria;
    
    private final List<ProfessorId> professorId;
}
