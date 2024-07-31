package com.carbigdata.ms.core.exception.client;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(){
        super("Client not found");
    }
}   
