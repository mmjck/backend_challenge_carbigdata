package com.carbigdata.ms.service.occurrence_images.impl;


import org.springframework.stereotype.Service;

import com.carbigdata.ms.domain.occurrences_image.entities.OccurrencesImage;
import com.carbigdata.ms.domain.occurrences_image.exceptions.OccurrencesImagesNotFoundException;
import com.carbigdata.ms.domain.occurrences_image.gateway.OccurrencesImageGateway;
import com.carbigdata.ms.domain.pagination.PaginationResponse;
import com.carbigdata.ms.service.occurrence_images.OccurrenceImagesService;

@Service
public class OccurrenceImagesServiceImpl implements OccurrenceImagesService {
    
    private final OccurrencesImageGateway gateway;

    private OccurrenceImagesServiceImpl(OccurrencesImageGateway gateway) {
        this.gateway = gateway;
    }

    public static OccurrenceImagesServiceImpl build(OccurrencesImageGateway gateway){
        return new OccurrenceImagesServiceImpl(gateway);
    }

    @Override
    public OccurrencesImage get(int id) {
        OccurrencesImage register =  this.gateway.findById(id);

        if(register == null){
            throw new OccurrencesImagesNotFoundException();
        }

        return register;
    }

    @Override
    public OccurrencesImage create(String hash,  String path, int occurrenceId) {

        var image = OccurrencesImage
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
    public OccurrencesImage update(int id, String hash,  String path) {
        OccurrencesImage register = this.gateway.findById(id);

        if(path != null && !path.equals(register.getPath())){
            register.setPath(path);
        }

        if(hash != null && !hash.equals(register.getHash())){
            register.setHash(hash);
        }

        // TODO: added update of occurrence_id
        return this.gateway.update(register);

    }

    @Override
    public PaginationResponse<OccurrencesImage> findAll(int page, int pageSize) {
        return this.gateway.findAll(page, pageSize);
    }

}