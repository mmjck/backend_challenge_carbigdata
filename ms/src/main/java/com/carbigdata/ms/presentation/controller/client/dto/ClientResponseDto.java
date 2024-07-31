package com.carbigdata.ms.presentation.controller.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ClientResponseDto(
    String name,
    String cpf,

    @JsonProperty(value = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") 
    LocalDateTime birthDate){
}
