package com.totvs.sl.school.core.disciplina.amqp.events;

import java.util.List;

import com.totvs.sl.school.core.professor.domain.model.ProfessorId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DisciplinaCriadaEvent {

    public static final transient String NAME = "DisciplinaCriadaEvent";
    
    private String disciplinaId;
    
    private String descricao;
    
    private String sigla;
    
    private int cargaHoraria;
    
    private List<ProfessorId> professorId;
}
