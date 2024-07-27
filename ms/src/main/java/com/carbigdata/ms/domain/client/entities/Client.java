package com.carbigdata.ms.domain.client.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private int id;
    private String fullName;
    private String cpf;
    private String password;
    
    private LocalDateTime birthDate;
    private LocalDateTime createdAt;

}
