package com.totvs.sl.school.core.amqp;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SchoolExchange {

    public static final String OUTPUT = "school-output-events";

    @Output(SchoolExchange.OUTPUT)
    MessageChannel output();

}
