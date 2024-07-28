package com.carbigdata.ms.controller.address;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.ms.controller.address.dto.CreateAddressRequestDTO;
import com.carbigdata.ms.controller.address.dto.UpdateAddressRequestDTO;
import com.carbigdata.ms.domain.address.entities.Address;
import com.carbigdata.ms.repositories.address.AddressJpaGateway;
import com.carbigdata.ms.repositories.address.jpa.AddressJpaRepository;
import com.carbigdata.ms.service.address.AddressService;
import com.carbigdata.ms.service.address.impl.AddressServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @SuppressWarnings("unused")
    private final AddressJpaRepository repository; 
    private final AddressService service;

    public AddressController(AddressJpaRepository repository) {

        this.repository = repository;
        AddressJpaGateway gateway = new AddressJpaGateway(repository);
        this.service = AddressServiceImpl.build(gateway);

    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody CreateAddressRequestDTO dto) {
        Address register = this.service.create(dto.state(), dto.city(), dto.zipCode(), dto.district());
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable("id") int id, @RequestBody UpdateAddressRequestDTO dto) {
        Address register = this.service.update(id, dto.state(), dto.city(), dto.zipCode(), dto.district());
        return ResponseEntity.ok().body(register);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable("id") int id) {
        Address client = this.service.get(id);
        return ResponseEntity.ok().body(client);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
}
