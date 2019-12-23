package com.totvs.sl.school.core.disciplina.amqp.events;

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
import com.totvs.sl.school.core.disciplina.application.CriarDisciplinaCommand;
import com.totvs.sl.school.core.disciplina.application.DisciplinaApplicationService;


@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = SchoolCoreApplication.class)
@Transactional
public class DisciplinaEventPublisherTest {
   
    @MockBean
    private SchoolExchange processor;

    @Autowired
    private DisciplinaApplicationService disciplinaApplicationService;

    @Before
    public void setup() {
        TestUtils.setAuthentication("b56efb27-13bb-4767-8227-77abd3761023");
        Mockito.when(processor.output()).thenReturn(Mockito.mock(MessageChannel.class));
    }

    @Test
    public void deveCriarAluno() {

        var cmd = CriarDisciplinaCommand.of( Fabrica.disciplinaDescricao1,  
                Fabrica.disciplinaSigla1,  
                Fabrica.disciplinaCargaHoraria1,
                Fabrica.umProfessorParaUmaDisciplina());

        disciplinaApplicationService.handle(cmd);

        ArgumentCaptor<Message<?>> argument = ArgumentCaptor.forClass(Message.class);
        verify(processor.output()).send(argument.capture());

        DisciplinaCriadaEvent payLoad = TestUtils.getMessagePayLoad(argument, DisciplinaCriadaEvent.class);
        
        assertThat(payLoad.getDisciplinaId()).isNotBlank();
        assertThat(payLoad.getDescricao()).isEqualTo(Fabrica.disciplinaDescricao1);
        assertThat(payLoad.getSigla()).isEqualTo(Fabrica.disciplinaSigla1);
        assertThat(payLoad.getCargaHoraria()).isEqualTo(Fabrica.disciplinaCargaHoraria1);
        assertThat(payLoad.getProfessorId()).size().isEqualTo(1);

    }     
}
