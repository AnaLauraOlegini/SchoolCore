package com.totvs.sl.school.core.turma.amqp.events;

import java.util.List;

import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaId;

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
public class TurmaCriadaEvent {

    public static final transient String NAME = "TurmaCriadaEvent";
    
    private String turmaId;
    
    private String descricao;
    
    private int anoLetivo;
    
    private int periodoLetivo;
    
    private int numeroVagas;
    
    private List<DisciplinaId> disciplinaId;
    
    private List<AlunoId> alunoId;
    
}
