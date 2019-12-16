package com.totvs.sl.school.core.turma.domain.model;

import java.util.UUID;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class TurmaId {
    private UUID id;
    
    private TurmaId(UUID id) {
        Assert.notNull(id, "O ID não deve ser nulo.");
        this.id = id;
    }
    
    public static TurmaId generate() {
        return new TurmaId(UUID.randomUUID());
    }
    
    @Override
    public String toString() {
        return id.toString();
    }
}
