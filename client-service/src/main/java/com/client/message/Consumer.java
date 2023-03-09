package com.client.message;

import com.client.dto.request.RequestClientRegister;
import com.client.service.ClientService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;


@Component
public class Consumer {

    @Autowired
    ClientService service;

    Logger logger =  LoggerFactory.getLogger(Consumer.class);

    @JmsListener(destination = "Send Money")
    public void recivemessage(String message) throws JMSException {
        RequestClientRegister client = new Gson().fromJson(message,RequestClientRegister.class);
        logger.info("mensaje recibido");


    }

}
