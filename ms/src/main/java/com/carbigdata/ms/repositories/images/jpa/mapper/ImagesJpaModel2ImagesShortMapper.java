package com.carbigdata.ms.repositories.images.jpa.mapper;

import com.carbigdata.ms.core.domain.images.entities.ImagesShort;
import com.carbigdata.ms.repositories.images.jpa.model.ImagesJpaModel;

import java.util.function.Function;

public class ImagesJpaModel2ImagesShortMapper implements Function<ImagesJpaModel, ImagesShort> {
    public static ImagesShort mapper(ImagesJpaModel model) {
        return new ImagesJpaModel2ImagesShortMapper().apply(model);
    }

    @Override
    public ImagesShort apply(ImagesJpaModel model) {
        return new ImagesShort(model.getHash(), model.getPath());
    }

}
