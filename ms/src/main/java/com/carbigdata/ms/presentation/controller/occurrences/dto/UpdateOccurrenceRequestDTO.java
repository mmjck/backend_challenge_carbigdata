package com.carbigdata.ms.presentation.controller.occurrences.dto;

import com.carbigdata.ms.core.domain.occurrences.OccurrencesStatus;

public record UpdateOccurrenceRequestDTO(
    OccurrencesStatus status
){

}