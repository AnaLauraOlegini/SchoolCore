package com.totvs.sl.school.core.amqp;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.totvs.tjf.core.message.TOTVSMessage;

@EnableBinding(SchoolExchange.class)
public class SchoolPublisher {
    
    private static final Logger LOG = LoggerFactory.getLogger(SchoolPublisher.class);

    @Autowired
    private SchoolExchange exchange;
    
    public <T> void publish(T event) {
        Field field;
        String eventName = null;
        try {
            field = event.getClass().getField("NAME");
            eventName = (String) field.get(event);
            
            new TOTVSMessage<T>(eventName, event).sendTo(exchange.output());
            
        } catch (Exception e) {
            LOG.debug("Exchange: {}\nTopic: {}\n", exchange, eventName);
        }       
    }
}
