package com.totvs.sl.school.core.aluno.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.totvs.sl.school.core.aluno.application.AlunoApplicationService;
import com.totvs.sl.school.core.aluno.application.CriarAlunoCommand;
import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.aluno.exception.SchoolCriarAlunoException;
import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion;
import com.totvs.tjf.core.validation.ValidatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(path = AlunoController.PATH, consumes = { APPLICATION_JSON_VALUE }, produces = {
        APPLICATION_JSON_VALUE })
@ApiGuideline(ApiGuidelineVersion.v1)
public class AlunoController {

	public static final String PATH = "/api/v1/alunos";

	@Autowired
	private AlunoApplicationService service;

	@Autowired
	private ValidatorService validador;

	@ApiOperation(value = "Criar Aluno.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Aluno criado."),
	        @ApiResponse(code = 400, message = "O Aluno não pode ser criado porque está em um estado inválido.") })
	@PostMapping
	ResponseEntity<Void> criar(@RequestBody CriarAlunoCommandDto dto) {

		validador.validate(dto).ifPresent(violations -> { throw new SchoolCriarAlunoException(violations); });

		var cmd = CriarAlunoCommand.of(dto.getNome(),
		                               CPF.of(dto.getCpf()),
		                               dto.getEmail(),
		                               dto.getFormaIngresso(),
		                               dto.getMatricula());

		AlunoId id = service.handle(cmd);

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
		                                                         .path("/")
		                                                         .path(id.toString())
		                                                         .build()
		                                                         .toUri())
		                     .build();
	}
}
