package com.carbigdata.ms.domain.client.gateway;

import com.carbigdata.ms.domain.client.entities.Client;

public interface ClientGateway {
    public Client save(Client data);
    public Client update(Client data);
    public void delete(int id);
    public Client findById(int id);
    public Client findByCpf(String id);

}
