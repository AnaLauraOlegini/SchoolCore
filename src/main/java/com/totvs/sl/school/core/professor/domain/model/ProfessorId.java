package com.totvs.sl.school.core.professor.domain.model;

import java.util.UUID;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class ProfessorId {
    
    private UUID id;
    
    private ProfessorId(UUID id) {
        Assert.notNull(id, "O ID não deve ser nulo.");
        this.id = id;
    }
    
    public static ProfessorId generate() {
        return new ProfessorId(UUID.randomUUID());
    }
    
    @Override
    public String toString() {
        return id.toString();
    }

}
