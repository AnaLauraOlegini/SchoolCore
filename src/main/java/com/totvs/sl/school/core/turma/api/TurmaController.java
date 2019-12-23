package com.totvs.sl.school.core.turma.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.totvs.sl.school.core.turma.application.CriarTurmaCommand;
import com.totvs.sl.school.core.turma.application.TurmaApplicationService;
import com.totvs.sl.school.core.turma.domain.model.TurmaId;
import com.totvs.sl.school.core.turma.exception.SchoolCriarTurmaException;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion;
import com.totvs.tjf.core.validation.ValidatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(path = TurmaController.PATH, consumes = { APPLICATION_JSON_VALUE }, produces = {
        APPLICATION_JSON_VALUE })
@ApiGuideline(ApiGuidelineVersion.v1)
public class TurmaController {

    public static final String PATH = "/api/v1/turmas";

    @Autowired
    private TurmaApplicationService service;

    @Autowired
    private ValidatorService validador;

    @ApiOperation(value = "Criar Turma.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Turma criado."),
            @ApiResponse(code = 400, message = "A turma não pode ser criado porque está em um estado inválido.") })
    @PostMapping
    ResponseEntity<Void> criar(@RequestBody CriarTurmaCommandDto dto) {

        validador.validate(dto).ifPresent(violations -> {
            throw new SchoolCriarTurmaException(violations);
        });

        var cmd = CriarTurmaCommand.of(dto.getDescricao(), dto.getAnoLetivo(), dto.getPeriodoLetivo(),
                dto.getNumeroVagas(), dto.getDisciplinaId(), dto.getAlunoId());
        
        TurmaId id = service.handle(cmd);

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(id.toString()).build().toUri())
                .build();
    }
}
