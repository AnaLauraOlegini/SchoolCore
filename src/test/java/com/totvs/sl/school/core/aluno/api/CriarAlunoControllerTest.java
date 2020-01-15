package com.totvs.sl.school.core.aluno.api;

import static com.totvs.tjf.mock.test.RacEmulator.HEADER_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

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

import com.totvs.sl.school.core.Fabrica;
import com.totvs.sl.school.core.TestUtils;
import com.totvs.sl.school.core.aluno.domain.model.Aluno;
import com.totvs.sl.school.core.aluno.domain.model.AlunoDomainRepository;
import com.totvs.sl.school.core.aluno.exception.SchoolCpfDoAlunoDuplicadoException;
import com.totvs.sl.school.core.aluno.exception.SchoolCriarAlunoException;
import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.tjf.mock.test.MockHttpServer;
import com.totvs.tjf.mock.test.RacEmulator;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CriarAlunoControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private AlunoDomainRepository alunoRepository;

	@BeforeClass
	public static void beforeClass() {
		TestUtils.setAuthentication("B56EFB27_13BB_4767_8227_77ABD3761023");
		MockHttpServer.getInstance();
	}

	private String jwt = RacEmulator.getInstance().generateJWT("user", "");

	@Test
	public void deveCriarAluno() throws Exception {

		CriarAlunoDto dto = new CriarAlunoDto(Fabrica.alunoNome1,
		                                      Fabrica.alunoCpf1,
		                                      Fabrica.alunoEmail1,
		                                      Fabrica.alunoFormaIngresso1,
		                                      Fabrica.alunoMatricula1);

		this.mock.perform(MockMvcRequestBuilders.post(AlunoController.PATH)
		                                        .header(HEADER_STRING, jwt)
		                                        .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                        .content(TestUtils.objectToJson(dto)))
		         .andExpect(status().is2xxSuccessful());

	}

	@Test
	public void naoDeveCriarAlunoComCamposNulos() throws Exception {

		CriarAlunoDto dto = new CriarAlunoDto(null,
		                                      Fabrica.alunoCpf2,
		                                      Fabrica.alunoEmail2,
		                                      null,
		                                      Fabrica.alunoMatricula2);

		MvcResult result = this.mock.perform(MockMvcRequestBuilders.post(AlunoController.PATH)
		                                                           .header(HEADER_STRING, jwt)
		                                                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                           .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse().getContentAsString()).contains(SchoolCriarAlunoException.class.getSimpleName());
		assertThat(result.getResponse().getContentAsString()).contains("CriarAlunoDTO.nome.NotBlank");
	}

	@Test
	public void naoDeveCriarAlunoComCpfInvalidoMenor() throws Exception {

		CriarAlunoDto dto = new CriarAlunoDto(Fabrica.alunoNome3,
		                                      Fabrica.alunoCpf3,
		                                      Fabrica.alunoEmail3,
		                                      Fabrica.alunoFormaIngresso3,
		                                      Fabrica.alunoMatricula3);

		MvcResult result = this.mock.perform(MockMvcRequestBuilders.post(AlunoController.PATH)
		                                                           .header(HEADER_STRING, jwt)
		                                                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                           .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse().getContentAsString()).contains(SchoolCriarAlunoException.class.getSimpleName());
		assertThat(result.getResponse().getContentAsString()).contains("CriarAlunoDTO.cpf.Size");
	}

	private void setupCriarAlunoComCpfDuplicado() {
		this.alunoRepository.insert(Aluno.builder()
		                                 .id(Fabrica.alunoId1)
		                                 .nome(Fabrica.alunoNome1)
		                                 .cpf(CPF.of(Fabrica.alunoCpf1))
		                                 .formaIngresso(Fabrica.alunoFormaIngresso1)
		                                 .matricula(Fabrica.alunoMatricula1)
		                                 .build());

	}

	@Test
	public void naoDeveCriarAlunoComCpfDuplicado() throws Exception {
		this.setupCriarAlunoComCpfDuplicado();

		CriarAlunoDto dto = new CriarAlunoDto(Fabrica.alunoNome1,
		                                      Fabrica.alunoCpf1,
		                                      Fabrica.alunoEmail1,
		                                      Fabrica.alunoFormaIngresso1,
		                                      Fabrica.alunoMatricula1);

		MvcResult result = this.mock.perform(MockMvcRequestBuilders.post(AlunoController.PATH)
		                                                           .header(HEADER_STRING, jwt)
		                                                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                           .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse().getContentAsString()).contains(SchoolCpfDoAlunoDuplicadoException.class.getSimpleName());
	}
}
