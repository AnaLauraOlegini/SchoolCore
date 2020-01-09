package com.totvs.sl.school.core.aluno.domain.model;

import java.util.UUID;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class AlunoId {

	private UUID id;

	private AlunoId(UUID id) {
		Assert.notNull(id, "O ID não deve ser nulo.");
		this.id = id;
	}

	public static AlunoId generate() {
		return new AlunoId(UUID.randomUUID());
	}

	public static AlunoId from(String id) {
		return new AlunoId(UUID.fromString(id));
	}

	@Override
	public String toString() {
		return id.toString();
	}
}
