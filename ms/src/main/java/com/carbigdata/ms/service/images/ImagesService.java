package com.carbigdata.ms.service.images;

import com.carbigdata.ms.core.domain.images.entities.Images;
import com.carbigdata.ms.presentation.controller.pagination.PaginationResponse;



public interface ImagesService {
    public Images get(int id);

    public PaginationResponse<Images> findAll(int page, int pageSize);
    
    public Images update(int id, String hash, String path);
    public Images create(String hash,  String path, int occurrenceId);

    public void delete(int id);
}
