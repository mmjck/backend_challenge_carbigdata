package com.carbigdata.ms.presentation.controller.images.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImagesResponseDTO(
    String hash,
    String path,
    
    
    @JsonProperty("occurrence_id")
    int occurrenceId,

    int id

) {
    
}
