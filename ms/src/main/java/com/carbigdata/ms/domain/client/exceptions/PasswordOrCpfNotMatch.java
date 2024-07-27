package com.carbigdata.ms.domain.client.exceptions;

public class PasswordOrCpfNotMatch extends RuntimeException {
    public PasswordOrCpfNotMatch(){
        super("Password or cpf not matches");
    }
}   
