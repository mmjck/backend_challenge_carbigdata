package com.carbigdata.ms.service.images.impl;

import org.springframework.stereotype.Service;

import com.carbigdata.ms.core.domain.images.entities.Images;
import com.carbigdata.ms.core.domain.images.gateway.ImagesGateway;
import com.carbigdata.ms.core.exception.images.ImagesNotFoundException;
import com.carbigdata.ms.presentation.controller.pagination.PaginationResponse;
import com.carbigdata.ms.service.images.ImagesService;

@Service
public class ImagesServiceImpl implements ImagesService {

    private final ImagesGateway gateway;

    public ImagesServiceImpl(ImagesGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Images get(int id) {
        Images register = this.gateway.findById(id);

        if (register == null) {
            throw new ImagesNotFoundException();
        }

        return register;
    }

    @Override
    public Images create(String hash, String path, int occurrenceId) {

        var image = Images
                .builder()
                .hash(hash)
                .path(path)
                .occurrenceId(occurrenceId)
                .build();

        return this.gateway.save(image);

    }

    @Override
    public void delete(int id) {
        this.gateway.delete(id);
    }

    @Override
    public Images update(int id, String hash, String path) {
        Images register = this.gateway.findById(id);

        if (path != null && !path.equals(register.getPath())) {
            register.setPath(path);
        }

        if (hash != null && !hash.equals(register.getHash())) {
            register.setHash(hash);
        }

        // TODO: added update of occurrence_id
        return this.gateway.update(register);

    }

    @Override
    public PaginationResponse<Images> findAll(int page, int pageSize) {
        return this.gateway.findAll(page, pageSize);
    }

}