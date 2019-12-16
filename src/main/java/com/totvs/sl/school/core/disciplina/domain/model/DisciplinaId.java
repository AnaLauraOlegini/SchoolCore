package com.totvs.sl.school.core.disciplina.domain.model;

import java.util.UUID;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class DisciplinaId {

    private UUID id;

    private DisciplinaId(UUID id) {
        Assert.notNull(id, "O ID não deve ser nulo.");
        this.id = id;
    }

    public static DisciplinaId generate() {
        return new DisciplinaId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}