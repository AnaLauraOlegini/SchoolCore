package com.totvs.sl.school.core.turma.amqp.events;

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
import com.totvs.sl.school.core.turma.application.CriarTurmaCommand;
import com.totvs.sl.school.core.turma.application.TurmaApplicationService;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = SchoolCoreApplication.class)
@Transactional
public class TurmaEventPublisherTest {

	@MockBean
	private SchoolExchange processor;

	@Autowired
	private TurmaApplicationService turmaApplicationService;

	@Before
	public void setup() {
		TestUtils.setAuthentication("b56efb27-13bb-4767-8227-77abd3761023");
		Mockito.when(processor.output()).thenReturn(Mockito.mock(MessageChannel.class));
	}

	@Test
	public void deveCriarAluno() {

		var cmd = CriarTurmaCommand.of(Fabrica.turmaDescricao1,
		                               Fabrica.turmaAnoLetivo,
		                               Fabrica.turmaPeriodoLetivo,
		                               Fabrica.turmaNumeroVagas,
		                               Fabrica.maisDeUmaDisciplinaParaUmaTurma(),
		                               Fabrica.maisDeUmAlunoParaUmaTurma());

		turmaApplicationService.handle(cmd);

		ArgumentCaptor<Message<?>> argument = ArgumentCaptor.forClass(Message.class);
		verify(processor.output()).send(argument.capture());

		TurmaCriadaEvent payLoad = TestUtils.getMessagePayLoad(argument, TurmaCriadaEvent.class);

		assertThat(payLoad.getTurmaId()).isNotBlank();
		assertThat(payLoad.getDescricao()).isEqualTo(Fabrica.turmaDescricao1);
		assertThat(payLoad.getAnoLetivo()).isEqualTo(Fabrica.turmaAnoLetivo);
		assertThat(payLoad.getPeriodoLetivo()).isEqualTo(Fabrica.turmaPeriodoLetivo);
		assertThat(payLoad.getNumeroVagas()).isEqualTo(Fabrica.turmaNumeroVagas);
		assertThat(payLoad.getAlunoId()).size().isEqualTo(3);
		assertThat(payLoad.getDisciplinaId()).size().isEqualTo(3);

	}
}
