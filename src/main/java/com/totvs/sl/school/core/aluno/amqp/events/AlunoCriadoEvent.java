package com.totvs.sl.school.core.aluno.amqp.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class AlunoCriadoEvent {

	public static final transient String NAME = "AlunoCriadoEvent";

	@NonNull
	private String alunoId;

	@NonNull
	private String nome;

	@NonNull
	private String cpf;

	@NonNull
	private String email;

	@NonNull
	private String formaIngresso;

	private int matricula;

}
