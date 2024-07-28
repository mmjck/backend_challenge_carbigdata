package com.carbigdata.ms.repositories.occurrences.jpa.mapper;

import java.util.function.Function;

import com.carbigdata.ms.domain.occurrences.entities.Occurrences;
import com.carbigdata.ms.repositories.occurrences.jpa.model.OccurrencesJpaModel;

public class Occurrences2OccurrencesJpaModel implements Function<Occurrences, OccurrencesJpaModel> {

    public static OccurrencesJpaModel mapper(final Occurrences model) {
        return new Occurrences2OccurrencesJpaModel().apply(model);
    }

    @Override
    public OccurrencesJpaModel apply(Occurrences model) {
        return new OccurrencesJpaModel(model.getId(), model.getClientId(),
                model.getAddressId(), model.getStatus(), model.getCreatedAt());
    }

}
