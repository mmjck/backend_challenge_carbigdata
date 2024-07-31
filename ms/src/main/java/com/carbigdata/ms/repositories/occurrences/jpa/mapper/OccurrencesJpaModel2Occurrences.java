package com.carbigdata.ms.repositories.occurrences.jpa.mapper;

import com.carbigdata.ms.core.domain.occurrences.Occurrences;
import com.carbigdata.ms.repositories.occurrences.jpa.model.OccurrencesJpaModel;
import java.util.function.Function;

public class OccurrencesJpaModel2Occurrences implements Function<OccurrencesJpaModel, Occurrences> {
    public static Occurrences mapper(OccurrencesJpaModel model) {
        return new OccurrencesJpaModel2Occurrences().apply(model);
    }

    @Override
    public Occurrences apply(OccurrencesJpaModel model) {
        return Occurrences
                .builder()
                .id(model.getId())
                .addressId(model.getAddressId())
                .clientId(model.getClientId())
                .createdAt(model.getCreatedAt())
                .status(model.getStatus())
                .build();
    }

}
