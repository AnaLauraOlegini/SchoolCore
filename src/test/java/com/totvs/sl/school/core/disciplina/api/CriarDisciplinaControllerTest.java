package com.totvs.sl.school.core.disciplina.api;

import static com.totvs.tjf.mock.test.RacEmulator.HEADER_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.totvs.sl.school.core.Fabrica;
import com.totvs.sl.school.core.TestUtils;
import com.totvs.sl.school.core.disciplina.exception.SchoolCriarDisciplinaException;
import com.totvs.tjf.mock.test.MockHttpServer;
import com.totvs.tjf.mock.test.RacEmulator;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CriarDisciplinaControllerTest {

	@Autowired
	private MockMvc mock;

	@BeforeClass
	public static void beforeClass() {
		TestUtils.setAuthentication("B56EFB27_13BB_4767_8227_77ABD3761023");
		MockHttpServer.getInstance();
	}

	private String jwt = RacEmulator.getInstance().generateJWT("user", "");

	@Test
	public void deveCriarDisciplina() throws Exception {

		CriarDisciplinaDto dto = new CriarDisciplinaDto(Fabrica.disciplinaDescricao2,
		                                                Fabrica.disciplinaSigla2,
		                                                Fabrica.disciplinaCargaHoraria2,
		                                                Fabrica.maisDeUmProfessorParaUmaDisciplina());

		this.mock.perform(MockMvcRequestBuilders.post(DisciplinaController.PATH)
		                                        .header(HEADER_STRING, jwt)
		                                        .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                        .content(TestUtils.objectToJson(dto)))
		         .andExpect(status().is2xxSuccessful());
	}

	@Test
	public void naoDeveCriarDisciplinaComCamposNulos() throws Exception {

		CriarDisciplinaDto dto = new CriarDisciplinaDto(Fabrica.disciplinaDescricao2,
		                                                null,
		                                                Fabrica.disciplinaCargaHoraria2,
		                                                Fabrica.maisDeUmProfessorParaUmaDisciplina());

		MvcResult result = this.mock.perform(MockMvcRequestBuilders.post(DisciplinaController.PATH)
		                                                           .header(HEADER_STRING, jwt)
		                                                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                           .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse()
		                 .getContentAsString()).contains(SchoolCriarDisciplinaException.class.getSimpleName());
		assertThat(result.getResponse().getContentAsString()).contains("CriarDisciplinaDTO.sigla.NotBlank");

	}

}
