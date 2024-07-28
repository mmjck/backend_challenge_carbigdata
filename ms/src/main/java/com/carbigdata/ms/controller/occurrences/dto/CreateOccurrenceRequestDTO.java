package com.carbigdata.ms.controller.occurrences.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOccurrenceRequestDTO(
    @JsonProperty("user_name") String userName,
    @JsonProperty("cpf") String cpf,


    String street,
    @JsonProperty("zip_code")
    String zipCode,
    String city,
    String state,
    String district

){

}