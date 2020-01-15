package com.totvs.sl.school.core.professor.amqp.events;

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
import org.springframework.transaction.annotation.Transactional;

import com.totvs.sl.school.core.Fabrica;
import com.totvs.sl.school.core.SchoolCoreApplication;
import com.totvs.sl.school.core.TestUtils;
import com.totvs.sl.school.core.amqp.SchoolExchange;
import com.totvs.sl.school.core.pessoa.domain.CPF;
import com.totvs.sl.school.core.professor.application.CriarProfessorCommand;
import com.totvs.sl.school.core.professor.application.ProfessorApplicationService;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = SchoolCoreApplication.class)
@Transactional
public class ProfessorEventPublisherTest {

	@MockBean
	private SchoolExchange processor;

	@Autowired
	private ProfessorApplicationService professorApplicationService;

	@Before
	public void setup() {
		TestUtils.setAuthentication("B56EFB27_13BB_4767_8227_77ABD3761023");
		Mockito.when(processor.output()).thenReturn(Mockito.mock(MessageChannel.class));
	}

	@Test
	public void deveCriarProfessor() {

		CriarProfessorCommand cmd = CriarProfessorCommand.of(Fabrica.professorNome1,
		                                                     CPF.of(Fabrica.professorCpf1),
		                                                     Fabrica.professorEmail1,
		                                                     Fabrica.professorTitulacao1);

		professorApplicationService.handle(cmd);

		ArgumentCaptor<Message<?>> argument = ArgumentCaptor.forClass(Message.class);
		verify(processor.output()).send(argument.capture());

		ProfessorCriadoEvent payLoad = TestUtils.getMessagePayLoad(argument, ProfessorCriadoEvent.class);

		assertThat(payLoad.getNome()).isEqualTo(Fabrica.professorNome1);
		assertThat(payLoad.getCpf().toString()).isEqualTo(Fabrica.professorCpf1);
		assertThat(payLoad.getEmail().toString()).isEqualTo(Fabrica.professorEmail1);
		assertThat(payLoad.getTitulacao().toString()).isEqualTo(Fabrica.professorTitulacao1);

	}
}
