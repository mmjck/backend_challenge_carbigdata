package com.carbigdata.ms.controller.occurrences.dto;

import com.carbigdata.ms.domain.occurrences.entities.OccurrencesStatus;

public record UpdateOccurrenceRequestDTO(
    OccurrencesStatus status
){

}