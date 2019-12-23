package com.totvs.sl.school.core.turma.api;

import static com.totvs.tjf.mock.test.RacEmulator.HEADER_STRING;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.totvs.sl.school.core.Fabrica;
import com.totvs.sl.school.core.SchoolCoreApplication;
import com.totvs.sl.school.core.TestUtils;
import com.totvs.sl.school.core.turma.domain.model.TurmaDomainRepository;
import com.totvs.tjf.mock.test.MockHttpServer;
import com.totvs.tjf.mock.test.RacEmulator;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = SchoolCoreApplication.class)
@Transactional
@AutoConfigureMockMvc
public class CriarTurmaControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private TurmaDomainRepository turmaDomainRepository;

    @BeforeClass
    public static void beforeClass() {
        TestUtils.setAuthentication("B56EFB27_13BB_4767_8227_77ABD3761023");
        MockHttpServer.getInstance();
    }

    private String jwt = RacEmulator.getInstance().generateJWT("user", "");

    @Test
    public void deveCriarDisciplina() throws Exception {

        var dto = new CriarTurmaCommandDto(Fabrica.turmaDescricao1, Fabrica.turmaAnoLetivo, Fabrica.turmaPeriodoLetivo, 
                Fabrica.turmaNumeroVagas, Fabrica.maisDeUmaDisciplinaParaUmaTurma(), Fabrica.maisDeUmAlunoParaUmaTurma());
                
        this.mock
                .perform(request(HttpMethod.POST, TurmaController.PATH)
                        .header(HEADER_STRING, jwt)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.objectToJson(dto)))
                .andExpect(status().is2xxSuccessful());

    }
    
}
