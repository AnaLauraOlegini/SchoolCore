package com.totvs.sl.school.core.professor.api;

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
import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.sl.school.core.professor.domain.model.Professor;
import com.totvs.sl.school.core.professor.domain.model.ProfessorDomainRepository;
import com.totvs.sl.school.core.professor.exception.SchoolCpfDoProfessorDuplicadoException;
import com.totvs.sl.school.core.professor.exception.SchoolCriarProfessorException;
import com.totvs.tjf.mock.test.MockHttpServer;
import com.totvs.tjf.mock.test.RacEmulator;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CriarProfessorControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ProfessorDomainRepository professorRepository;

	@BeforeClass
	public static void beforeClass() {
		TestUtils.setAuthentication("B56EFB27_13BB_4767_8227_77ABD3761023");
		MockHttpServer.getInstance();
	}

	private String jwt = RacEmulator.getInstance().generateJWT("user", "");

	@Test
	public void deveCriarProfessor() throws Exception {

		CriarProfessorDto dto = new CriarProfessorDto(Fabrica.professorNome1,
		                                              Fabrica.professorCpf1,
		                                              Fabrica.professorEmail1,
		                                              Fabrica.professorTitulacao1);

		this.mock.perform(MockMvcRequestBuilders.post(ProfessorController.PATH)
		                                        .header(HEADER_STRING, jwt)
		                                        .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                        .content(TestUtils.objectToJson(dto)))
		         .andExpect(status().is2xxSuccessful());
	}

	@Test
	public void naoDeveCriarProfessorComCamposNulos() throws Exception {

		CriarProfessorDto dto = new CriarProfessorDto(Fabrica.professorNome2,
		                                              Fabrica.professorCpf2,
		                                              Fabrica.professorEmail2,
		                                              null);

		MvcResult result = this.mock.perform(MockMvcRequestBuilders.post(ProfessorController.PATH)
		                                                           .header(HEADER_STRING, jwt)
		                                                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                           .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse()
		                 .getContentAsString()).contains(SchoolCriarProfessorException.class.getSimpleName());
		assertThat(result.getResponse().getContentAsString()).contains("CriarProfessorDTO.titulacao.NotBlank");

	}

	@Test
	public void naoDeveCriarProfessorComCpfInvalidoMenor() throws Exception {

		CriarProfessorDto dto = new CriarProfessorDto(Fabrica.professorNome3,
		                                              Fabrica.professorCpf3,
		                                              Fabrica.professorEmail3,
		                                              Fabrica.professorTitulacao3);

		MvcResult result = this.mock.perform(MockMvcRequestBuilders.post(ProfessorController.PATH)
		                                                           .header(HEADER_STRING, jwt)
		                                                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                           .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse()
		                 .getContentAsString()).contains(SchoolCriarProfessorException.class.getSimpleName());
		assertThat(result.getResponse().getContentAsString()).contains("CriarProfessorDTO.cpf.Size");
	}

	private void setupCriarProfessorComCpfDuplicado() {
		this.professorRepository.insert(Professor.builder()
		                                         .id(Fabrica.professorId2)
		                                         .nome(Fabrica.professorNome2)
		                                         .cpf(CPF.of(Fabrica.professorCpf2))
		                                         .email(Fabrica.professorEmail2)
		                                         .titulacao(Fabrica.professorTitulacao2)
		                                         .build());
	}

	@Test
	public void naoDeveIncluirProfessorComCpfDuplicado() throws Exception {
		this.setupCriarProfessorComCpfDuplicado();

		CriarProfessorDto dto = new CriarProfessorDto(Fabrica.professorNome2,
		                                              Fabrica.professorCpf2,
		                                              Fabrica.professorEmail2,
		                                              Fabrica.professorTitulacao2);

		MvcResult result = this.mock.perform(MockMvcRequestBuilders.post(ProfessorController.PATH)
		                                                           .header(HEADER_STRING, jwt)
		                                                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                           .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse()
		                 .getContentAsString()).contains(SchoolCpfDoProfessorDuplicadoException.class.getSimpleName());

	}

}