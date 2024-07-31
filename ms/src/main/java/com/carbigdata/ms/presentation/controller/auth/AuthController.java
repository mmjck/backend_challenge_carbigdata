package com.carbigdata.ms.presentation.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.ms.presentation.controller.auth.dto.AuthResponseDTO;
import com.carbigdata.ms.presentation.controller.auth.dto.LoginClientRequestDTO;
import com.carbigdata.ms.presentation.controller.auth.dto.RegisterClientRequestDTO;
import com.carbigdata.ms.service.auth.AuthService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService service;


    public AuthController(AuthService service) {
        this.service = service;

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
