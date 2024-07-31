package com.carbigdata.ms.service.auth.impl;


import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.carbigdata.ms.core.domain.client.Client;
import com.carbigdata.ms.core.domain.client.gateway.ClientGateway;
import com.carbigdata.ms.core.exception.client.ClientAlreadyExistsException;
import com.carbigdata.ms.core.exception.client.ClientNotFoundException;
import com.carbigdata.ms.core.exception.client.PasswordOrCpfNotMatch;
import com.carbigdata.ms.infra.security.TokenService;
import com.carbigdata.ms.service.auth.AuthService;


@Service
public class AuthServiceImpl implements AuthService{
    private final ClientGateway gateway;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthServiceImpl(ClientGateway gateway, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    

    @Override
    public String register(String name, String cpf, String password, LocalDateTime birthDate) {
        Client findedUser = this.gateway.findByCpf(cpf);

        if(findedUser != null){
            throw new ClientAlreadyExistsException();
        }
        Client client = Client.builder()
            .birthDate(birthDate)
            .cpf(cpf)
            .password(passwordEncoder.encode(password))
            .fullName(name)
            .build();
            
            
        this.gateway.save(client);

        String token = this.tokenService.generateToken(client);
        return token;
    }

    @Override
    public String login(String cpf, String password) {
        Client client = this.gateway.findByCpf(cpf);
        
        if(client == null){
            throw new ClientNotFoundException();
        } 

        if (!passwordEncoder.matches(password, client.getPassword())) {
            throw new PasswordOrCpfNotMatch();    
        }

        String token = this.tokenService.generateToken(client);
        return token;

    }
    
}
