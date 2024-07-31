package com.carbigdata.ms.presentation.controller.address;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.ms.core.domain.address.Address;
import com.carbigdata.ms.presentation.controller.address.dto.AddressResponseDTO;
import com.carbigdata.ms.presentation.controller.address.dto.CreateAddressRequestDTO;
import com.carbigdata.ms.presentation.controller.address.dto.UpdateAddressRequestDTO;
import com.carbigdata.ms.service.address.AddressService;
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

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service =  service;
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody CreateAddressRequestDTO dto) {
        Address register = this.service.create(dto.state(), dto.city(), dto.zipCode(), dto.district());
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> update(@PathVariable("id") int id, @RequestBody UpdateAddressRequestDTO dto) {
        Address register = this.service.update(id, dto.state(), dto.city(), dto.zipCode(), dto.district());
        return ResponseEntity.ok().body(new AddressResponseDTO(register.getId(), register.getZipCode(), register.getCity(), register.getState(), register.getDistrict()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getById(@PathVariable("id") int id) {
        Address register = this.service.get(id);
        return ResponseEntity.ok().body(new AddressResponseDTO(register.getId(), register.getZipCode(), register.getCity(), register.getState(), register.getDistrict()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
}
