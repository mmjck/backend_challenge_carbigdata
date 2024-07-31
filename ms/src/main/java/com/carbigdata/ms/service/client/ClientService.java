package com.carbigdata.ms.service.client;

import java.time.LocalDateTime;

import com.carbigdata.ms.core.domain.client.Client;

public interface ClientService {
    public Client get(int id);
    public Client findByCpf(String cpf);
    public Client update(int id, String name, String cpf, LocalDateTime birthDate);

    public void delete(int id);
}
