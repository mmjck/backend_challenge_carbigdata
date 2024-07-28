package com.carbigdata.ms.repositories.occurrences_image;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.carbigdata.ms.domain.client.exceptions.ClientNotFoundException;
import com.carbigdata.ms.domain.occurrences_image.entities.OccurrencesImage;
import com.carbigdata.ms.domain.occurrences_image.gateway.OccurrencesImageGateway;
import com.carbigdata.ms.domain.pagination.PaginationResponse;
import com.carbigdata.ms.repositories.occurrences_image.jpa.OccurrencesImageJpaRepository;
import com.carbigdata.ms.repositories.occurrences_image.jpa.mapper.OccurrencesImage2OccurrencesImageJpaModel;
import com.carbigdata.ms.repositories.occurrences_image.jpa.mapper.OccurrencesImageJpaModel2OccurrencesImage;
import com.carbigdata.ms.repositories.occurrences_image.jpa.model.OccurrencesImageJpaModel;

@Component
public class OccurrencesImageJpaGateway implements OccurrencesImageGateway {

    private final OccurrencesImageJpaRepository repository;

    public OccurrencesImageJpaGateway(OccurrencesImageJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public OccurrencesImage save(OccurrencesImage data) {

        var entity = OccurrencesImage2OccurrencesImageJpaModel.mapper(data);
        var register = this.repository.save(entity);

        return OccurrencesImageJpaModel2OccurrencesImage.mapper(register);
    }

    @Override
    public OccurrencesImage update(OccurrencesImage data) {
        var entity = OccurrencesImage2OccurrencesImageJpaModel.mapper(data);
        var register = this.repository.saveAndFlush(entity);

        return OccurrencesImageJpaModel2OccurrencesImage.mapper(register);
    }

    @Override
    public void delete(int id) {
        this.repository.findById(id).orElseThrow(() -> new ClientNotFoundException());
        this.repository.deleteById(id);
    }

    @Override
    public OccurrencesImage findById(int id) {
        Optional<OccurrencesImageJpaModel> register = this.repository.findById(id);

        if (register.isEmpty()) {
            return null;
        }

        return OccurrencesImageJpaModel2OccurrencesImage.mapper(register.get());
    }

    @Override
    public PaginationResponse<OccurrencesImage> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<OccurrencesImageJpaModel> response = this.repository.findAll(pageable);
        List<OccurrencesImage> occurrences = response.getContent().stream().map(OccurrencesImageJpaModel2OccurrencesImage::mapper).toList();
        
        return new PaginationResponse<OccurrencesImage>(response.getNumber(), response.getTotalElements(), pageSize, occurrences);
    }

}
