package com.totvs.sl.school.core.professor.amqp.events;

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
public final class ProfessorCriadoEvent {

	public static final transient String NAME = "ProfessorCriadoEvent";

	@NonNull
	private String professorId;

	@NonNull
	private String cpf;

	@NonNull
	private String nome;

	@NonNull
	private String email;

	@NonNull
	private String titulacao;

}
