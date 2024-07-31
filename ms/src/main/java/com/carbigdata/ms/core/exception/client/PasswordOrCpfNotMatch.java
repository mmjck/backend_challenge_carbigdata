package com.carbigdata.ms.core.exception.client;

public class PasswordOrCpfNotMatch extends RuntimeException {
    public PasswordOrCpfNotMatch(){
        super("Password or cpf not matches");
    }
}   
