package com.totvs.sl.school.core.turma.domain.model;

import javax.validation.constraints.NotNull;

import com.totvs.tjf.core.stereotype.Aggregate;
import com.totvs.tjf.core.stereotype.AggregateIdentifier;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Aggregate
@NoArgsConstructor
public class Turma {

    @NotNull
    @AggregateIdentifier
    private TurmaId id;

    @NotNull
    private String descricao;

    private int anoLetivo;
    private int periodoLetivo;
    private int numeroVagas;
    
    @Builder
    public Turma(TurmaId id, String descricao, int anoLetivo, int periodoLetivo, int numeroVagas) {
        this.id = id;
        this.descricao = descricao;
        this.anoLetivo = anoLetivo;
        this.periodoLetivo = periodoLetivo;
        this.numeroVagas = numeroVagas;
    }

}
