package com.carbigdata.ms.domain.client.exceptions;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(){
        super("Client already exists");
    }
}   
