package com.app.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExcepcionPersonalizada extends RuntimeException{
    //private static final long serialVersionID = 1L;
    public ExcepcionPersonalizada(String mensaje){
        super(mensaje);
    }
}
