package com.carbigdata.ms.presentation.controller.address.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateAddressRequestDTO(
    String street,
    
    @JsonProperty("zip_code")
    String zipCode,
    String city,
    String state,
    String district
){

}