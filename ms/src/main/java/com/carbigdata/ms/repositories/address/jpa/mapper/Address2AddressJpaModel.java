package com.carbigdata.ms.repositories.address.jpa.mapper;

import java.util.function.Function;
import com.carbigdata.ms.domain.address.entities.Address;
import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;

public class Address2AddressJpaModel implements Function<Address, AddressJpaModel>  {

    public static AddressJpaModel mapper(final Address profile) {
        return new Address2AddressJpaModel().apply(profile);
    }

    @Override
    public AddressJpaModel apply(Address client) {
        return new AddressJpaModel(client.getId(), client.getState(), client.getCity(), client.getDistrict(), client.getZipCode(), client.getCreatedAt());
        
    }
    

}
