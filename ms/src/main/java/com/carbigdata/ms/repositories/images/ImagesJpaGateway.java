package com.carbigdata.ms.repositories.images;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.carbigdata.ms.core.domain.images.entities.Images;
import com.carbigdata.ms.core.domain.images.gateway.ImagesGateway;
import com.carbigdata.ms.core.exception.client.ClientNotFoundException;
import com.carbigdata.ms.presentation.controller.pagination.PaginationResponse;
import com.carbigdata.ms.repositories.images.jpa.ImagesJpaRepository;
import com.carbigdata.ms.repositories.images.jpa.mapper.Images2ImageJpaModelMapper;
import com.carbigdata.ms.repositories.images.jpa.mapper.Image2JpaModelMapper;
import com.carbigdata.ms.repositories.images.jpa.model.ImagesJpaModel;

@Component
public class ImagesJpaGateway implements ImagesGateway {

    private final ImagesJpaRepository repository;

    public ImagesJpaGateway(ImagesJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Images save(Images data) {

        var entity = Images2ImageJpaModelMapper.mapper(data);
        var register = this.repository.save(entity);

        return Image2JpaModelMapper.mapper(register);
    }

    @Override
    public Images update(Images data) {
        var entity = Images2ImageJpaModelMapper.mapper(data);
        var register = this.repository.saveAndFlush(entity);

        return Image2JpaModelMapper.mapper(register);
    }

    @Override
    public void delete(int id) {
        this.repository.findById(id).orElseThrow(() -> new ClientNotFoundException());
        this.repository.deleteById(id);
    }

    @Override
    public Images findById(int id) {
        Optional<ImagesJpaModel> register = this.repository.findById(id);

        if (register.isEmpty()) {
            return null;
        }

        return Image2JpaModelMapper.mapper(register.get());
    }

    @Override
    public PaginationResponse<Images> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ImagesJpaModel> response = this.repository.findAll(pageable);
        List<Images> occurrences = response.getContent().stream().map(Image2JpaModelMapper::mapper).toList();
        
        return new PaginationResponse<Images>(response.getNumber(), response.getTotalElements(), pageSize, occurrences);
    }

}
