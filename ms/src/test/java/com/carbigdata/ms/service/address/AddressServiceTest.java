package com.carbigdata.ms.service.address;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.carbigdata.ms.core.exception.address.AddressNotFoundException;
import com.carbigdata.ms.repositories.address.AddressJpaGateway;
import com.carbigdata.ms.repositories.address.jpa.AddressJpaRepository;
import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;
import com.carbigdata.ms.service.address.impl.AddressServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;;

public class AddressServiceTest {
    @Mock
    AddressJpaRepository repository;

    private AddressService service;

    @InjectMocks
    AddressServiceImpl impl;

    @BeforeEach
    void init() {

        MockitoAnnotations.openMocks(this);

        AddressJpaGateway gateway = new AddressJpaGateway(this.repository);
        // this.service = AddressServiceImpl.build(gateway);
    }

    @Test
    void testCreate() {
        String city = "city";
        String state = "state";
        String zipCode = "zipCode";
        String district = "district";

        var model = new AddressJpaModel(1, state, city, district, zipCode, LocalDateTime.now());
        when(this.repository.save(any(AddressJpaModel.class))).thenReturn(model);
        var response = this.service.create(state, city, zipCode, district);

        assertNotNull(response);

    }

    @Test
    void testGetById() {
        String city = "city";
        String state = "state";
        String zipCode = "zipCode";
        String district = "district";

        var model = new AddressJpaModel(1, state, city, district, zipCode, LocalDateTime.now());

        when(this.repository.save(any(AddressJpaModel.class))).thenReturn(model);
        this.repository.save(model);

        when(this.repository.findById(1)).thenReturn(Optional.of(model));

        var response = this.service.get(1);
        assertNotNull(response);

    }

    @Test
    void testUpdateWithError() {
        String city = "city";
        String state = "state";
        String zipCode = "zipCode";
        String district = "district";

        when(this.repository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class, () -> {
            this.service.update(100, state, city, zipCode, district);
        });
    }

    // @Test(sk)
    // void testUpdate() {
    // String city = "city";
    // String state = "state";
    // String zipCode = "zipCode";
    // String district = "district";

    // var model = new AddressJpaModel(1, state, city, district, zipCode,
    // LocalDateTime.now());

    // when(this.repository.save(any(AddressJpaModel.class))).thenReturn(model);
    // // this.repository.save(model);

    // when(this.repository.findById(1)).thenReturn(Optional.of(model));

    // // when(this.repository.save(any(AddressJpaModel.class))).thenReturn(model);

    // var response = this.service.update(1, "new-state", city, zipCode, district);

    // assertNotNull(response);
    // assertThat(response.getState()).isEqualTo("new-state");

    // }

}
