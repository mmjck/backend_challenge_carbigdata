package com.carbigdata.ms.repositories.images.jpa.mapper;


import java.util.function.Function;

import com.carbigdata.ms.core.domain.images.entities.Images;
import com.carbigdata.ms.repositories.images.jpa.model.ImagesJpaModel;

public class Images2ImageJpaModelMapper implements Function<Images, ImagesJpaModel>  {


    public static ImagesJpaModel mapper(final Images model) {
        return new Images2ImageJpaModelMapper().apply(model);
    }


    @Override
    public ImagesJpaModel apply(final Images model) {
        return new ImagesJpaModel(model.getId(), model.getOccurrenceId(), model.getHash(), model.getPath(), model.getCreatedAt());
    }
    

}
