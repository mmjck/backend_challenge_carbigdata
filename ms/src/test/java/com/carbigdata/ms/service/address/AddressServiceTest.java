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

import com.carbigdata.ms.core.domain.address.gateway.AddressGateway;
import com.carbigdata.ms.core.exception.address.AddressNotFoundException;
import com.carbigdata.ms.repositories.address.AddressJpaGateway;
import com.carbigdata.ms.repositories.address.jpa.AddressJpaRepository;
import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;
import com.carbigdata.ms.service.address.impl.AddressServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

// @ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    private AddressJpaRepository repository;

    @Mock
    private AddressService mocked;

    @Mock
    private AddressGateway gateway;

    @Mock
    private AddressJpaGateway addressJpaGateway;

    @InjectMocks
    private AddressServiceImpl service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
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

        when(this.repository.findById(anyInt())).thenReturn(Optional.of(model));

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

}
