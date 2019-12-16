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
import org.springframework.transaction.annotation.Transactional;

import com.totvs.sl.school.core.SchoolCoreApplication;
import com.totvs.sl.school.core.TestUtils;
import com.totvs.sl.school.core.aluno.application.AlunoApplicationService;
import com.totvs.sl.school.core.aluno.application.CriarAlunoCommand;
import com.totvs.sl.school.core.amqp.SchoolExchange;
import com.totvs.sl.school.core.pessoa.domain.CPF;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = SchoolCoreApplication.class)
@Transactional
public class AlunoEventPublisherTest {

    @MockBean
    private SchoolExchange processor;

    @Autowired
    private AlunoApplicationService alunoApplicationService;

    @Before
    public void setup() {
        TestUtils.setAuthentication("b56efb27-13bb-4767-8227-77abd3761023");
        Mockito.when(processor.output()).thenReturn(Mockito.mock(MessageChannel.class));
    }

    @Test
    public void deveCriarAluno() {

        var nome = "Sophia Tânia Sueli da Conceição";
        var cpf = "02131505906";
        var email = "sophiataniasuelidaconceicao@hotmail.com.br";
        var formaIngresso = "ENADE";
        var matricula = 876543210;

        var cmd = CriarAlunoCommand.of(nome, CPF.of(cpf), email, formaIngresso, matricula);

        alunoApplicationService.handle(cmd);

        ArgumentCaptor<Message<?>> argument = ArgumentCaptor.forClass(Message.class);
        verify(processor.output()).send(argument.capture());

        AlunoCriadoEvent payLoad = TestUtils.getMessagePayLoad(argument, AlunoCriadoEvent.class);

        assertThat(payLoad.getAlunoId()).isNotBlank();
        assertThat(payLoad.getNome()).isEqualTo("Sophia Tânia Sueli da Conceição");
        assertThat(payLoad.getCpf()).isEqualTo("02131505906");
        assertThat(payLoad.getEmail()).isEqualTo("sophiataniasuelidaconceicao@hotmail.com.br");
        assertThat(payLoad.getFormaIngresso()).isEqualTo("ENADE");
        assertThat(payLoad.getMatricula()).isEqualTo(876543210);

    }

}
