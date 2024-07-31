package com.carbigdata.ms.repositories.client.jpa.mapper;


import com.carbigdata.ms.core.domain.client.Client;
import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;

import java.util.function.Function;

public class ClientJpaModel2Client implements Function<ClientJpaModel, Client> {
    public static Client mapper(final ClientJpaModel client) {
        return new ClientJpaModel2Client().apply(client);
    }

    @Override
    public Client apply(ClientJpaModel client) {
        return new Client(
            client.getId(), 
                client.getFullName(), 
                client.getCpf(),
                client.getPassword(), 
                client.getBirthDate(),
                client.getCreatedAt());
    }
}
