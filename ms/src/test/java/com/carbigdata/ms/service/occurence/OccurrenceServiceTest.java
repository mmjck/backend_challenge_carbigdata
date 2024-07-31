package com.carbigdata.ms.service.occurence;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.carbigdata.ms.core.domain.occurrences.OccurrencesStatus;
import com.carbigdata.ms.repositories.occurrences.OccurrencesJpaGateway;
import com.carbigdata.ms.repositories.occurrences.jpa.OccurrencesJpaRepository;
import com.carbigdata.ms.repositories.occurrences.jpa.model.OccurrencesJpaModel;
import com.carbigdata.ms.service.occurrence.OccurrenceService;
import com.carbigdata.ms.service.occurrence.impl.OccurrenceServiceImpl;
public class OccurrenceServiceTest {

    @Mock
    private OccurrencesJpaRepository repository;

    @Mock
    private OccurrenceService mocker;


    @Mock
    private OccurrencesJpaGateway gateway;

    @InjectMocks
    private OccurrenceServiceImpl service;

    @BeforeEach
    void init() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testCreate() {
        OccurrencesJpaModel model = new OccurrencesJpaModel(1, 1, 2, OccurrencesStatus.ACTIVE, LocalDateTime.now());

        when(this.repository.save(any(OccurrencesJpaModel.class))).thenReturn(model);


        var response = this.service.create(1, 1);
        assertNotNull(response);
        assertThat(response.getClientId()).isEqualTo(1);
        assertThat(response.getAddressId()).isEqualTo(2);

    }

    @Test
    void testDelete() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFinished() {
        int id = 1;
        int clientId = 1;
        int addressId = 1;
        OccurrencesJpaModel model = new OccurrencesJpaModel(id, clientId, addressId, OccurrencesStatus.ACTIVE, LocalDateTime.now());

        this.repository.save(model);
        when(this.repository.save(any(OccurrencesJpaModel.class))).thenReturn(model);
        when(this.repository.findById(anyInt())).thenReturn(Optional.of(model));
        
        
        verify(this.repository, times(1)).save(any(OccurrencesJpaModel.class));


        this.service.finished(id);
        verify(this.repository, times(2)).save(any(OccurrencesJpaModel.class));
    }

    @Test
    void testGet() {

        int id = 1;
        int clientId = 1;
        int addressId = 1;
        OccurrencesJpaModel model = new OccurrencesJpaModel(id, clientId, addressId, OccurrencesStatus.ACTIVE, LocalDateTime.now());

        this.repository.save(model);
        when(this.repository.findById(anyInt())).thenReturn(Optional.of(model));

        var response = this.service.get(id);
        verify(this.repository, times(1)).findById(anyInt());
        assertNotNull(response);
        assertThat(response.getAddressId()).isEqualTo(addressId);
        assertThat(response.getClientId()).isEqualTo(clientId);
    }

    @Test
    void testUpdate() {

    }
}
