package com.carbigdata.ms.domain.address.exceptions;


public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(){
        super("Address not found");
    }
}   
