package com.carbigdata.ms.core.domain.images.gateway;

import com.carbigdata.ms.core.domain.images.entities.Images;
import com.carbigdata.ms.presentation.controller.pagination.PaginationResponse;


public interface ImagesGateway {
    public Images save(Images data);
    public Images update(Images data);
    public void delete(int id);
    public Images findById(int id);
    public PaginationResponse<Images> findAll(int page, int pageSize);


}
