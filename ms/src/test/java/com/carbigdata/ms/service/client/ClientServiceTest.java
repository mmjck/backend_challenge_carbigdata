package com.carbigdata.ms.service.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.carbigdata.ms.core.exception.client.ClientNotFoundException;
import com.carbigdata.ms.repositories.client.ClientJpaGateway;
import com.carbigdata.ms.repositories.client.jpa.ClientJpaRepository;
import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;
import com.carbigdata.ms.service.client.impl.ClientServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

public class ClientServiceTest {

    @Mock
    ClientJpaRepository repository;

    @Mock
    PasswordEncoder passwordEncoder;

    private ClientService service;

    @InjectMocks
    ClientServiceImpl impl;

    @BeforeEach
    void init() {

        MockitoAnnotations.openMocks(this);
        ClientJpaGateway gateway = new ClientJpaGateway(this.repository);
    }

    @Test
    void testDelete() { }

    @Test
    void testFindByCpf() {
        int id = 1;
        String fullName = "fullName";
        String password = "password";
        String cpf = "cpf";
        LocalDateTime birthDate = LocalDateTime.of(2024, 07, 01, 01, 01, 01);
        LocalDateTime createdAt = LocalDateTime.of(2024, 07, 01, 01, 01, 01);
        ClientJpaModel model = new ClientJpaModel(id, fullName, cpf, password, birthDate, createdAt);

        this.repository.save(model);
        when(this.repository.findByCpf(cpf)).thenReturn(Optional.of(model));

        var response = this.service.findByCpf(cpf);

        assertNotNull(response);
        assertThat(response.getCpf()).isEqualTo(cpf);
    }

    @Test
    void testFindByCpfNotFound() {
        String cpf = "000";
        when(this.repository.findByCpf(cpf)).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> {
            this.service.findByCpf(cpf);
        });
    }


    @Test
    void testGet() {
        int id = 1;
        String fullName = "fullName";
        String password = "password";
        String cpf = "cpf";
        LocalDateTime birthDate = LocalDateTime.of(2024, 07, 01, 01, 01, 01);
        LocalDateTime createdAt = LocalDateTime.of(2024, 07, 01, 01, 01, 01);
        ClientJpaModel model = new ClientJpaModel(id, fullName, cpf, password, birthDate, createdAt);

        this.repository.save(model);
        when(this.repository.findById(anyInt())).thenReturn(Optional.of(model));
        var response = this.service.get(id);
        assertNotNull(response);
        assertThat(response.getId()).isEqualTo(id);
    }

    @Test
    void testFindByIdNotFound() {
        when(this.repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> {
            this.service.get(anyInt());
        });
    }


    @Test
    void testUpdate() {

    }
}
