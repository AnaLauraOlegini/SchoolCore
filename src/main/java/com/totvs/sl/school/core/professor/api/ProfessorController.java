package com.totvs.sl.school.core.professor.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.sl.school.core.professor.application.CriarProfessorCommand;
import com.totvs.sl.school.core.professor.application.ProfessorApplicationService;
import com.totvs.sl.school.core.professor.domain.model.ProfessorId;
import com.totvs.sl.school.core.professor.exception.SchoolCriarProfessorException;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion;
import com.totvs.tjf.core.validation.ValidatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(path = ProfessorController.PATH, consumes = { APPLICATION_JSON_VALUE }, produces = {
        APPLICATION_JSON_VALUE })
@ApiGuideline(ApiGuidelineVersion.v1)
public class ProfessorController {

	public static final String PATH = "/api/v1/professores";

	@Autowired
	private ProfessorApplicationService service;

	@Autowired
	private ValidatorService validador;

	@ApiOperation(value = "Criar Professor.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Professor criado."),
	        @ApiResponse(code = 400, message = "O professor não pode ser criado porque está em um estado inválido.") })
	@PostMapping
	ResponseEntity<Void> criar(@RequestBody CriarProfessorCommandDto dto) {

		validador.validate(dto).ifPresent(violations -> { throw new SchoolCriarProfessorException(violations); });

		var cmd = CriarProfessorCommand.of(dto.getNome(), CPF.of(dto.getCpf()), dto.getEmail(), dto.getTitulacao());

		ProfessorId id = service.handle(cmd);

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
		                                                         .path("/")
		                                                         .path(id.toString())
		                                                         .build()
		                                                         .toUri())
		                     .build();
	}

}
