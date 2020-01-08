package com.totvs.sl.school.core.professor.api;

import static com.totvs.tjf.mock.test.RacEmulator.HEADER_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.totvs.sl.school.core.SchoolCoreApplication;
import com.totvs.sl.school.core.TestUtils;
import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.sl.school.core.professor.domain.model.Professor;
import com.totvs.sl.school.core.professor.domain.model.ProfessorDomainRepository;
import com.totvs.sl.school.core.professor.domain.model.ProfessorId;
import com.totvs.tjf.mock.test.MockHttpServer;
import com.totvs.tjf.mock.test.RacEmulator;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = SchoolCoreApplication.class)
@Transactional
@AutoConfigureMockMvc
public class CriarProfessorControllerTest {

	@Autowired
	private MockMvc mock;

	// @Autowired
	private ProfessorDomainRepository professorRepository;

	@BeforeClass
	public static void beforeClass() {
		TestUtils.setAuthentication("b56efb27-13bb-4767-8227-77abd3761023");
		MockHttpServer.getInstance();
	}

	private String jwt = RacEmulator.getInstance().generateJWT("user", "");

	@Test
	@Description("Inclusão de Professor OK")
	public void deveCriarProfessor() throws Exception {

		var dto = new CriarProfessorDto("Manoel Sérgio da Cunha",
		                                "33699891909",
		                                "manoelsergiodacunha-84@gmail.com",
		                                "PHD");

		this.mock.perform(request(HttpMethod.POST,
		                          ProfessorController.PATH).header(HEADER_STRING, jwt)
		                                                   .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                   .content(TestUtils.objectToJson(dto)))
		         .andExpect(status().is2xxSuccessful());
	}

	@Test
	@Description("Inclusão de Professor com campos nulos")
	public void naoDeveCriarProfessorComCamposNulos() throws Exception {

		var dto = new CriarProfessorDto(null, null, null, null);

		MvcResult result = this.mock.perform(request(HttpMethod.POST,
		                                             ProfessorController.PATH).header(HEADER_STRING, jwt)
		                                                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                                      .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("SchoolCpfDeveSerValidoException");
	}

	@Test
	@Description("Inclusao de Professor com CPF duplicado.")
	public void naoDeveIncluirProfessorComCpfDuplicado() throws Exception {

		Professor professor = Professor.builder()
		                               .id(ProfessorId.generate())
		                               .nome("Beatriz Isadora Mendes")
		                               .cpf(CPF.of("59637547800"))
		                               .email("beatrizisadoramendes_@hotmail.com")
		                               .titulacao("PHD")
		                               .build();

		this.professorRepository.insert(professor);

		CriarProfessorDto dto = new CriarProfessorDto("Beatriz Isadora Mendes",
		                                              "59637547800",
		                                              "beatrizisadoramendes_@hotmail.com",
		                                              "PHD");

		MvcResult result = this.mock.perform(request(HttpMethod.POST,
		                                             ProfessorController.PATH).header(HEADER_STRING, jwt)
		                                                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                                      .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("SchoolCpfDoProfessorDuplicadoException");

	}

}