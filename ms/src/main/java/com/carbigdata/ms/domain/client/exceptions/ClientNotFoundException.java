package com.carbigdata.ms.domain.client.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(){
        super("Client not found");
    }
}   
