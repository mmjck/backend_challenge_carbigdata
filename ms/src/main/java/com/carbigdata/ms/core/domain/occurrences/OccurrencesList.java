package com.carbigdata.ms.core.domain.occurrences;

import com.carbigdata.ms.core.domain.images.entities.ImagesShort;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record OccurrencesList(
        Integer id,
        @JsonProperty(value = "created_at")
        String createdAt,
        String status,
        String cpf,

        @JsonProperty(value = "full_name")
        String fullName,

        @JsonProperty(value = "client_id")
        Integer clientId,
        
        String state,

        @JsonProperty(value = "zip_code")
        String zipCode,
        String district,
        String city,
        List<ImagesShort> images
        ) {

}
