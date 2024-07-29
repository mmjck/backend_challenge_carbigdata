package com.carbigdata.ms.service.occurrence_images;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.when;
import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.carbigdata.ms.domain.occurrences_image.gateway.OccurrencesImageGateway;
import com.carbigdata.ms.repositories.occurrences_image.OccurrencesImageJpaGateway;
import com.carbigdata.ms.repositories.occurrences_image.jpa.OccurrencesImageJpaRepository;
import com.carbigdata.ms.repositories.occurrences_image.jpa.model.OccurrencesImageJpaModel;
import com.carbigdata.ms.service.occurrence_images.impl.OccurrenceImagesServiceImpl;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;


public class OccurrenceImagesServiceTest {

    @Mock
    OccurrencesImageJpaRepository repository;

    private OccurrenceImagesService service;

    @InjectMocks
    OccurrenceImagesServiceImpl impl;

    @BeforeEach
    void init() {

        MockitoAnnotations.openMocks(this);

        OccurrencesImageGateway gateway = new OccurrencesImageJpaGateway(this.repository);
        this.service = OccurrenceImagesServiceImpl.build(gateway);
    }

    @Test
    void testCreate() {
        String hash = "hash";
        String path = "path";
        int occurrenceId = 1;
        int id = 1;
        var model = new OccurrencesImageJpaModel(id, occurrenceId, hash, path, LocalDateTime.now());
        when(this.repository.save(any(OccurrencesImageJpaModel.class))).thenReturn(model);



        var response = this.service.create(hash, path, occurrenceId);
        assertNotNull(response);
        assertThat(response.getOccurrenceId()).isEqualTo(occurrenceId);

    }

    @Test
    void testDelete() { }

    @Test
    void testFindAll() {
        String hash = "hash";
        String path = "path";
        int occurrenceId = 1;
        int id = 1;
        var model = new OccurrencesImageJpaModel(id, occurrenceId, hash, path, LocalDateTime.now());



        int page = 0;
        int pageSize = 10;
        
        List<OccurrencesImageJpaModel> images = Arrays.asList(model);
        Pageable pageable = PageRequest.of(page, pageSize);

        // Create a Page object
        Page<OccurrencesImageJpaModel> p = new PageImpl<>(images, pageable, images.size());

        when(this.repository.findAll(pageable)).thenReturn(p);

        var response = this.service.findAll(page, pageSize);
        assertNotNull(response);
        assertThat(response.total()).isEqualTo(1);
        assertThat(response.page()).isEqualTo(0);
        assertThat(response.data().size()).isEqualTo(1);
    }

    @Test
    void testGet() {
        String hash = "hash";
        String path = "path";
        int occurrenceId = 1;
        int id = 1;
        var model = new OccurrencesImageJpaModel(id, occurrenceId, hash, path, LocalDateTime.now());


        when(this.repository.findById(anyInt())).thenReturn(Optional.of(model));


        var response  = this.service.get(id);
        assertNotNull(response);
        assertThat(response.getOccurrenceId()).isEqualTo(occurrenceId);

    }

    @Test
    void testUpdate() {
        String hash = "hash";
        String path = "path";
        int occurrenceId = 1;
        int id = 1;
        var model = new OccurrencesImageJpaModel(id, occurrenceId, hash, path, LocalDateTime.now());


        this.repository.save(model);
        when(this.repository.findById(anyInt())).thenReturn(Optional.of(model));

        model.setPath("new-path");
        when(this.repository.saveAndFlush(any(OccurrencesImageJpaModel.class))).thenReturn(model);
        var response  = this.service.update(id, hash, "new-path");



        assertNotNull(response);
        assertThat(response.getOccurrenceId()).isEqualTo(occurrenceId);
        assertThat(response.getPath()).isEqualTo("new-path");
    }
}
