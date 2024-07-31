package com.carbigdata.ms.repositories.images.jpa.mapper;

import com.carbigdata.ms.core.domain.images.entities.Images;
import com.carbigdata.ms.repositories.images.jpa.model.ImagesJpaModel;

import java.util.function.Function;

public class Image2JpaModelMapper implements Function<ImagesJpaModel, Images> {
    public static Images mapper(final ImagesJpaModel model) {
        return new Image2JpaModelMapper().apply(model);
    }

    @Override
    public Images apply(ImagesJpaModel model) {
        return new Images(model.getId(), model.getOccurrenceId(), model.getHash(), model.getPath(),
                model.getCreatedAt());
    }
}
