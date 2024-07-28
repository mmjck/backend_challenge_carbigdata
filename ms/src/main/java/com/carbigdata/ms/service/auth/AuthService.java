package com.carbigdata.ms.service.auth;

import java.time.LocalDateTime;
public interface AuthService {
    public String register(String name, String cpf, String password, LocalDateTime birthDate);
    public String login(String cpf, String password);
}
