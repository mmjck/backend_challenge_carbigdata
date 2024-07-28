package com.carbigdata.ms.service.client.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carbigdata.ms.domain.client.entities.Client;
import com.carbigdata.ms.domain.client.exceptions.ClientNotFoundException;
import com.carbigdata.ms.domain.client.gateway.ClientGateway;
import com.carbigdata.ms.service.client.ClientService;

import java.time.LocalDateTime;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientGateway gateway;

    @SuppressWarnings("unused")
    private final PasswordEncoder passwordEncoder;

    private ClientServiceImpl(ClientGateway gateway, PasswordEncoder passwordEncoder) {
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
    }

    public static ClientServiceImpl build(ClientGateway gateway, PasswordEncoder passwordEncoder) {
        return new ClientServiceImpl(gateway, passwordEncoder);
    }

    public static ClientServiceImpl with(ClientGateway gateway) {
        return new ClientServiceImpl(gateway, null);
    }

    @Override
    public Client get(int id) {
        Client client = this.gateway.findById(id);

        if (client == null) {
            throw new ClientNotFoundException();

        }
        return client;
    }

    @Override
    public void delete(int id) {
        this.gateway.delete(id);
    }

    @Override
    public Client update(int id, String name, String cpf, LocalDateTime birthDate) {
        Client client = this.gateway.findById(id);

        if (client == null) {
            throw new ClientNotFoundException();

        }

        if (name != null && !name.equals(client.getFullName())) {
            client.setFullName(name);
        }

        if (cpf != null && !cpf.equals(client.getCpf())) {
            client.setCpf(cpf);
        }

        if (birthDate != null && !birthDate.equals(client.getBirthDate())) {
            client.setBirthDate(birthDate);
        }

        return this.gateway.save(client);

    }

    @Override
    public Client findByCpf(String cpf) {
        Client client = this.gateway.findByCpf(cpf);

        if (client == null) {
            throw new ClientNotFoundException();
        }

        return client;
    }

}