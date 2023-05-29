package com.Crud_Example.exception;

import org.springframework.http.HttpStatus;

public class ClientAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ClientAPIException(HttpStatus status, String message){
        super(message);

        this.status=status;
        this.message= message;
    }
}
