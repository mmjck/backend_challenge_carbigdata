package com.carbigdata.ms.core.exception.client;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(){
        super("Client already exists");
    }
}   
