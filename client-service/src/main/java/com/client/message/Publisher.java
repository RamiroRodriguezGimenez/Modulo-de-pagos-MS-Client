package com.client.message;


import com.commons.clients.models.entity.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Autowired
    JmsTemplate jmsTemplate;
    Logger logger =  LoggerFactory.getLogger(Publisher.class);

    public void sendMessage(Client message) throws JsonProcessingException {
        logger.info("Se envia el siguiente mensaje: "+message);
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String messageJson = objectMapper.writeValueAsString(message);

            jmsTemplate.convertAndSend("Register", messageJson);
        } catch (Exception e){
            logger.error(e.getMessage());
        }

    }
}
