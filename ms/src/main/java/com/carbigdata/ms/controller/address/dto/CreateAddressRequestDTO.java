package com.carbigdata.ms.controller.address.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateAddressRequestDTO(
    String street,
    
    @JsonProperty("zip_code")
    String zipCode,
    String city,
    String state,
    String district
){

}