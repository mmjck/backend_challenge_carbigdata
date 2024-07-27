package com.carbigdata.ms.domain.address.gateway;
import com.carbigdata.ms.domain.address.entities.Address;

public interface AddressGateway {
    public Address save(Address data);
    public Address update(Address data);
    public Address findById(int id);
    public void delete(int id);

}
