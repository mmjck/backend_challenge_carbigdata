package com.carbigdata.ms.domain.occurrences_image.gateway;

import com.carbigdata.ms.domain.occurrences_image.entities.OccurrencesImage;
import com.carbigdata.ms.domain.pagination.PaginationResponse;


public interface OccurrencesImageGateway {
    public OccurrencesImage save(OccurrencesImage data);
    public OccurrencesImage update(OccurrencesImage data);
    public void delete(int id);
    public OccurrencesImage findById(int id);
    public PaginationResponse<OccurrencesImage> findAll(int page, int pageSize);


}
