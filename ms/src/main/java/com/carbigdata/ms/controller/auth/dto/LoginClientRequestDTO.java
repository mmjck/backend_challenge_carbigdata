package com.carbigdata.ms.controller.auth.dto;

import org.jetbrains.annotations.NotNull;

public record LoginClientRequestDTO(
    @NotNull String cpf,
    @NotNull String password
) {
    
}
