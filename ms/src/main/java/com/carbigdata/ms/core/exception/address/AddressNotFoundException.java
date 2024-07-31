package com.carbigdata.ms.core.exception.address;


public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(){
        super("Address not found");
    }
}   
