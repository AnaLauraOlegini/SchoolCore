package com.totvs.sl.school.core.aluno.amqp.events;

import com.totvs.sl.school.core.aluno.domain.model.Aluno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class AlunoCriadoEvent {

	public static final String NAME = "AlunoCriadoEvent";

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

	
	public static AlunoCriadoEvent from(Aluno aluno) {
		return AlunoCriadoEvent.builder()
									 .alunoId(aluno.getId().toString())
									 .nome(aluno.getNome())
									 .cpf(aluno.getCpf().getNumero())
									 .email(aluno.getEmail())
									 .formaIngresso(aluno.getFormaIngresso())
									 .matricula(aluno.getMatricula())
									 .build();
	}
	
}
