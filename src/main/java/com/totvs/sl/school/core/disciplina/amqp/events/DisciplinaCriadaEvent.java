package com.totvs.sl.school.core.disciplina.amqp.events;

import java.util.List;

import com.totvs.sl.school.core.disciplina.domain.enums.Disciplinas;

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
public class DisciplinaCriadaEvent {

	public static final String NAME = "DisciplinaCriadaEvent";
	
	@NonNull
	private String disciplinaId;
	
	@NonNull
	private String descricao;
	
	@NonNull
	private Disciplinas sigla;
	
	private int cargaHoraria;

	@NonNull
	private List<String> professorId;
}
