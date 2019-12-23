package com.totvs.sl.school.core.disciplina.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.totvs.sl.school.core.disciplina.application.CriarDisciplinaCommand;
import com.totvs.sl.school.core.disciplina.application.DisciplinaApplicationService;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaId;
import com.totvs.sl.school.core.disciplina.exception.SchoolCriarDisciplinaException;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion;
import com.totvs.tjf.core.validation.ValidatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(path = DisciplinaController.PATH, consumes = { APPLICATION_JSON_VALUE }, produces = {
        APPLICATION_JSON_VALUE })
@ApiGuideline(ApiGuidelineVersion.v1)
public class DisciplinaController {

    public static final String PATH = "/api/v1/disciplinas";

    @Autowired
    private DisciplinaApplicationService service;

    @Autowired
    private ValidatorService validador;

    @ApiOperation(value = "Criar Disciplina.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Disciplina criado."),
            @ApiResponse(code = 400, message = "A disciplina não pode ser criado porque está em um estado inválido.") })
    @PostMapping
    ResponseEntity<Void> criar(@RequestBody CriarDisciplinaCommandDto dto) {

        validador.validate(dto).ifPresent(violations -> {
            throw new SchoolCriarDisciplinaException(violations);
        });

        var cmd = CriarDisciplinaCommand.of(dto.getDescricao(), dto.getSigla(), dto.getCargaHoraria(),
                dto.getProfessorId());
        DisciplinaId id = service.handle(cmd);

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(id.toString()).build().toUri())
                .build();
    }

}
