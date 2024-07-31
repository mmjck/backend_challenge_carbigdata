package com.carbigdata.ms.repositories.client;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.carbigdata.ms.core.domain.client.Client;
import com.carbigdata.ms.core.domain.client.gateway.ClientGateway;
import com.carbigdata.ms.repositories.client.jpa.ClientJpaRepository;
import com.carbigdata.ms.repositories.client.jpa.mapper.Client2ClientJpaModel;
import com.carbigdata.ms.repositories.client.jpa.mapper.ClientJpaModel2Client;
import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;

@Component
public class ClientJpaGateway implements ClientGateway {

    private final ClientJpaRepository repository;

    public ClientJpaGateway(ClientJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client save(Client data) {

        var entity = Client2ClientJpaModel.mapper(data);
        var client = this.repository.save(entity);
        return ClientJpaModel2Client.mapper(client);
    }

    @Override
    public Client update(Client data) {
        var entity = Client2ClientJpaModel.mapper(data);
        var client = this.repository.save(entity);

        return ClientJpaModel2Client.mapper(client);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public Client findById(int id) {
        Optional<ClientJpaModel> client = this.repository.findById(id);

        if(client.isEmpty()){
            return null;
        }

        return ClientJpaModel2Client.mapper(client.get());
    }

    @Override
    public Client findByCpf(String cpf) {
        Optional<ClientJpaModel> client = this.repository.findByCpf(cpf);


        if(!client.isPresent()){
            return null;
        }

        return ClientJpaModel2Client.mapper(client.get());
    }
    
}
