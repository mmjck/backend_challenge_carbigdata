package com.carbigdata.ms.repositories.client.jpa.mapper;

import java.util.function.Function;

import com.carbigdata.ms.core.domain.client.Client;
import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;

public class Client2ClientJpaModel implements Function<Client, ClientJpaModel> {

    public static ClientJpaModel mapper(final Client client) {
        return new Client2ClientJpaModel().apply(client);
    }

    @Override
    public ClientJpaModel apply(Client client) {
        return new ClientJpaModel(client.getId(), client.getFullName(), client.getCpf(), client.getPassword(),
                client.getBirthDate(), client.getCreatedAt());
    }

}
