package com.carbigdata.ms.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.ms.controller.auth.dto.AuthResponseDTO;
import com.carbigdata.ms.controller.auth.dto.LoginClientRequestDTO;
import com.carbigdata.ms.controller.auth.dto.RegisterClientRequestDTO;
import com.carbigdata.ms.infra.secutiry.TokenService;
import com.carbigdata.ms.repositories.client.ClientJpaGateway;
import com.carbigdata.ms.repositories.client.jpa.ClientJpaRepository;
import com.carbigdata.ms.service.auth.AuthService;
import com.carbigdata.ms.service.auth.impl.AuthServiceImpl;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ClientJpaRepository repository; 
    private final AuthService service;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;


    public AuthController(ClientJpaRepository repository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        ClientJpaGateway gateway = new ClientJpaGateway(repository);
        this.service = AuthServiceImpl.build(gateway, passwordEncoder, tokenService);

    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> create(@RequestBody @Validated RegisterClientRequestDTO dto) {
        String token = this.service.register(dto.name(), dto.cpf(), dto.password(), dto.birthDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Validated LoginClientRequestDTO dto) {
        String token = this.service.login(dto.cpf(), dto.password());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(token));
    }

    
}
