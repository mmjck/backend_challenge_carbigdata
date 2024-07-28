package com.carbigdata.ms.service.occurrence_images;

import com.carbigdata.ms.domain.occurrences_image.entities.OccurrencesImage;
import com.carbigdata.ms.domain.pagination.PaginationResponse;



public interface OccurrenceImagesService {
    public OccurrencesImage get(int id);

    public PaginationResponse<OccurrencesImage> findAll(int page, int pageSize);
    
    public OccurrencesImage update(int id, String hash, String path);
    public OccurrencesImage create(String hash,  String path, int occurrenceId);

    public void delete(int id);
}
