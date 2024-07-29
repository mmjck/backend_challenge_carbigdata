package com.carbigdata.ms.service.address.impl;

import org.springframework.stereotype.Service;

import com.carbigdata.ms.domain.address.entities.Address;
import com.carbigdata.ms.domain.address.exceptions.AddressNotFoundException;
import com.carbigdata.ms.domain.address.gateway.AddressGateway;
import com.carbigdata.ms.service.address.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressGateway gateway;

    private AddressServiceImpl(AddressGateway gateway) {
        this.gateway = gateway;
    }

    public static AddressServiceImpl build(AddressGateway gateway) {
        return new AddressServiceImpl(gateway);
    }

    @Override
    public Address get(int id) {
        return this.gateway.findById(id);
    }

    @Override
    public Address create(
            String state,
            String city,
            String zipCode,
            String district) {

        var client = Address
                .builder()
                .city(city)
                .state(state)
                .district(district)
                .zipCode(zipCode)
                .build();

        return this.gateway.save(client);

    }

    @Override
    public void delete(int id) {
        this.gateway.delete(id);
    }

    @Override
    public Address update(int id,
            String state,
            String city,
            String zipCode,
            String district) {
        Address address = this.gateway.findById(id);

        if (address == null) {
            throw new AddressNotFoundException();
        }
            
        if (state != null && !state.equals(address.getState())) {
            address.setState(state);
        }

        if (city != null && !city.equals(address.getCity())) {
            address.setCity(city);
        }

        if (zipCode != null && !zipCode.equals(address.getZipCode())) {
            address.setZipCode(zipCode);
        }

        if (district != null && !district.equals(address.getDistrict())) {
            address.setDistrict(district);
        }

        return this.gateway.save(address);

    }

}