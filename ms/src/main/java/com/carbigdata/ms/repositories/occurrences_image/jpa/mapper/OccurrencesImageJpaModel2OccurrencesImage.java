package com.carbigdata.ms.repositories.occurrences_image.jpa.mapper;

import com.carbigdata.ms.domain.occurrences_image.entities.OccurrencesImage;
import com.carbigdata.ms.repositories.occurrences_image.jpa.model.OccurrencesImageJpaModel;

import java.util.function.Function;

public class OccurrencesImageJpaModel2OccurrencesImage implements Function<OccurrencesImageJpaModel, OccurrencesImage> {
    public static OccurrencesImage mapper(final OccurrencesImageJpaModel model) {
        return new OccurrencesImageJpaModel2OccurrencesImage().apply(model);
    }

    @Override
    public OccurrencesImage apply(OccurrencesImageJpaModel model) {
        return new OccurrencesImage(model.getId(), model.getOccurenceId(), model.getHash(), model.getPath(),
                model.getCreatedAt());
    }
}
