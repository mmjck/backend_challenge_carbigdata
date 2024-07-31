package com.carbigdata.ms.core.domain.client.gateway;

import com.carbigdata.ms.core.domain.client.Client;

public interface ClientGateway {
    public Client save(Client data);
    public Client update(Client data);
    public void delete(int id);
    public Client findById(int id);
    public Client findByCpf(String id);

}
