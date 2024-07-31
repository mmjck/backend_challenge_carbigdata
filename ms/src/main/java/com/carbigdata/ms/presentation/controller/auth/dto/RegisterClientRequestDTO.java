package com.carbigdata.ms.presentation.controller.auth.dto;



import java.time.LocalDateTime;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterClientRequestDTO(
    @NotNull String name,
    @NotNull String password,
    @NotNull String cpf,

    @JsonProperty(value = "birth_date")
    @NotNull 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") 
    LocalDateTime birthDate
){

}