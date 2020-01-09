package com.totvs.sl.school.core.aluno.api;

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
import com.totvs.sl.school.core.aluno.domain.model.Aluno;
import com.totvs.sl.school.core.aluno.domain.model.AlunoDomainRepository;
import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.tjf.mock.test.MockHttpServer;
import com.totvs.tjf.mock.test.RacEmulator;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = SchoolCoreApplication.class)
@Transactional
@AutoConfigureMockMvc
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
	@Description("Inclusão de Aluno OK")
	public void deveCriarAluno() throws Exception {

		var dto = new CriarAlunoDto("Rosa Malu Clara Moura",
		                            "51573728926",
		                            "rosamaluclaramoura_@regional.com.br",
		                            "ENADE",
		                            123456789);

		this.mock.perform(request(HttpMethod.POST, AlunoController.PATH).header(HEADER_STRING, jwt)
		                                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                                .content(TestUtils.objectToJson(dto)))
		         .andExpect(status().is2xxSuccessful());

	}

	@Test
	@Description("Inclusão de Aluno com campos nulos")
	public void naoDeveCriarAlunoComCamposNulos() throws Exception {

		var dto = new CriarAlunoDto(null, null, null, null, 0);

		MvcResult result = this.mock.perform(request(HttpMethod.POST,
		                                             AlunoController.PATH).header(HEADER_STRING, jwt)
		                                                                  .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                                  .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("SchoolCpfDeveSerValidoException");
	}

	@Test
	@Description("Inclusão de Aluno com CPF duplicado")
	public void naoDeveCriarAlunoComCpfDuplicado() throws Exception {

		Aluno aluno = Aluno.builder()
		                   .id(AlunoId.generate())
		                   .nome("Victor Marcelo Santos")
		                   .cpf(CPF.of("33511897963"))
		                   .email("vvictormarcelosantos@formulaweb.com.br")
		                   .matricula(168223223)
		                   .formaIngresso("ENADE")
		                   .build();

		this.alunoRepository.insert(aluno);

		CriarAlunoDto dto = new CriarAlunoDto("Victor Marcelo Santos",
		                                      "33511897963",
		                                      "vvictormarcelosantos@formulaweb.com.br",
		                                      "ENADE",
		                                      168223223);

		MvcResult result = this.mock.perform(request(HttpMethod.POST,
		                                             AlunoController.PATH).header(HEADER_STRING, jwt)
		                                                                  .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                                  .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("SchoolCpfDoAlunoDuplicadoException");
	}

	@Test
	@Description("Inclusão de Aluno com CPF valor invalido - menor")
	public void naoDeveCriarAlunoComCpfInvalidoMenor() throws Exception {

		var dto = new CriarAlunoDto("Henry Alexandre Ian da Luz",
		                            "306398229",
		                            "hhenryalexandreiandaluz@genesyslab.com",
		                            "VESTIBULAR",
		                            408603999);

		MvcResult result = this.mock.perform(request(HttpMethod.POST,
		                                             AlunoController.PATH).header(HEADER_STRING, jwt)
		                                                                  .contentType(MediaType.APPLICATION_JSON_VALUE)
		                                                                  .content(TestUtils.objectToJson(dto)))
		                            .andExpect(status().isBadRequest())
		                            .andReturn();

		assertThat(result.getResponse().getContentAsString()).contains("SchoolCpfDeveSerValidoException");
	}

}
