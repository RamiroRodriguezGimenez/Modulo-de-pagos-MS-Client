package com.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(){super();}

    public NotFoundException(String messagge) {super(messagge);}
}
