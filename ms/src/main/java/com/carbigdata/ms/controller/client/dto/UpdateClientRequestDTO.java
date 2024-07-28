package com.carbigdata.ms.controller.client.dto;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateClientRequestDTO(
    String name,
    @JsonProperty(value = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime birthDate,
    String cpf
){

}