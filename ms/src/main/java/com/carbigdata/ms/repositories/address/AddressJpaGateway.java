package com.carbigdata.ms.repositories.address;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.carbigdata.ms.core.domain.address.Address;
import com.carbigdata.ms.core.domain.address.gateway.AddressGateway;
import com.carbigdata.ms.core.exception.address.AddressNotFoundException;
import com.carbigdata.ms.repositories.address.jpa.AddressJpaRepository;
import com.carbigdata.ms.repositories.address.jpa.mapper.Address2AddressJpaModel;
import com.carbigdata.ms.repositories.address.jpa.mapper.AddressJpaModel2Address;
import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;

@Component
// Use annotation @Repository
public class AddressJpaGateway implements AddressGateway {

    private final AddressJpaRepository repository;

    public AddressJpaGateway(AddressJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Address save(Address data) {

        var entity = Address2AddressJpaModel.mapper(data);
        var client = this.repository.save(entity);

        return AddressJpaModel2Address.mapper(client);
    }

    @Override
    public Address update(Address data) {
        var entity = Address2AddressJpaModel.mapper(data);
        var client = this.repository.save(entity);

        return AddressJpaModel2Address.mapper(client);
    }

    @Override
    public void delete(int id) {
        this.repository.findById(id).orElseThrow(() -> new AddressNotFoundException());
        this.repository.deleteById(id);
    }

    @Override
    public Address findById(int id) {
        Optional<AddressJpaModel> address = this.repository.findById(id);

        if (address.isEmpty()) {
            return null;
        }

        return AddressJpaModel2Address.mapper(address.get());
    }

}
