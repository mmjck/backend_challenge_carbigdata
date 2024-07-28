package com.carbigdata.ms.repositories.occurrences_image.jpa.mapper;


import java.util.function.Function;
import com.carbigdata.ms.domain.occurrences_image.entities.OccurrencesImage;
import com.carbigdata.ms.repositories.occurrences_image.jpa.model.OccurrencesImageJpaModel;

public class OccurrencesImage2OccurrencesImageJpaModel implements Function<OccurrencesImage, OccurrencesImageJpaModel>  {


    public static OccurrencesImageJpaModel mapper(final OccurrencesImage profile) {
        return new OccurrencesImage2OccurrencesImageJpaModel().apply(profile);
    }


    @Override
    public OccurrencesImageJpaModel apply(OccurrencesImage model) {
        return new OccurrencesImageJpaModel(model.getId(), model.getOccurrenceId(), model.getHash(), model.getPath(), model.getCreatedAt());
    }
    

}
