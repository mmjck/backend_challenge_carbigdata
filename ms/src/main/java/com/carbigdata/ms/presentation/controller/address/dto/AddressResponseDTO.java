package com.carbigdata.ms.presentation.controller.address.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressResponseDTO(
    int id,

    @JsonProperty("zip_code")
    String zipCode,
    String city,
    String state,
    String district
) {
    
};