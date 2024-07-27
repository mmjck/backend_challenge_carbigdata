package com.carbigdata.ms.repositories.address.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;


public interface AddressJpaRepository extends JpaRepository<AddressJpaModel, Integer>{
    
}
