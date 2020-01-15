package com.totvs.sl.school.core.aluno.amqp.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.totvs.sl.school.core.Fabrica;
import com.totvs.sl.school.core.TestUtils;
import com.totvs.sl.school.core.aluno.application.AlunoApplicationService;
import com.totvs.sl.school.core.aluno.application.CriarAlunoCommand;
import com.totvs.sl.school.core.amqp.SchoolExchange;
import com.totvs.sl.school.core.pessoa.domain.CPF;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
public class AlunoEventPublisherTest {

	@MockBean
	private SchoolExchange processor;

	@MockBean(name = SchoolExchange.OUTPUT)
	private MessageChannel output;

	@Autowired
	private AlunoApplicationService alunoApplicationService;
	
	@Before
	public void setup() {
		TestUtils.setAuthentication("B56EFB27_13BB_4767_8227_77ABD3761023");
		Mockito.when(processor.output()).thenReturn(Mockito.mock(MessageChannel.class));
	}

	@Test
	public void deveCriarAluno() {

		CriarAlunoCommand cmd = CriarAlunoCommand.of(Fabrica.alunoNome1,
		                                             CPF.of(Fabrica.alunoCpf1),
		                                             Fabrica.alunoEmail1,
		                                             Fabrica.alunoFormaIngresso1,
		                                             Fabrica.alunoMatricula1);

		alunoApplicationService.handle(cmd);

		ArgumentCaptor<Message<?>> argument = ArgumentCaptor.forClass(Message.class);
		verify(processor.output()).send(argument.capture());

		AlunoCriadoEvent payLoad = TestUtils.getMessagePayLoad(argument, AlunoCriadoEvent.class);

		assertThat(payLoad.getAlunoId()).isNotBlank();
		assertThat(payLoad.getNome()).isEqualTo(Fabrica.alunoNome1);
		assertThat(payLoad.getCpf()).isEqualTo(Fabrica.alunoCpf1);
		assertThat(payLoad.getEmail()).isEqualTo(Fabrica.alunoEmail1);
		assertThat(payLoad.getFormaIngresso()).isEqualTo(Fabrica.alunoFormaIngresso1);
		assertThat(payLoad.getMatricula()).isEqualTo(Fabrica.alunoMatricula1);

	}

}
