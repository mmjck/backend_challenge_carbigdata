package com.carbigdata.ms.service.address;

import com.carbigdata.ms.core.domain.address.Address;

public interface AddressService {
    public Address get(int id);

    public Address update(int id, String state,
            String city,
            String zipCode,
            String district);

    public Address create(String state,
            String city,
            String zipCode,
            String district);

    public void delete(int id);
}


