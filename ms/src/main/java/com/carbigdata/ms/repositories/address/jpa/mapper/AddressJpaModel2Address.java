package com.carbigdata.ms.repositories.address.jpa.mapper;



import com.carbigdata.ms.domain.address.entities.Address;
import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;

import java.util.function.Function;

public class AddressJpaModel2Address implements Function<AddressJpaModel, Address> {
    public static Address mapper(final AddressJpaModel address) {
        return new AddressJpaModel2Address().apply(address);
    }

    @Override
    public Address apply(AddressJpaModel client) {
        return new Address(client.getId(), client.getState(), client.getCity(), client.getZipCode(), client.getDistrict(), client.getCreatedAt());
        
    }
}
