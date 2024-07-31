package com.carbigdata.ms.presentation.controller.images.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateImagesRequestDTO(
   @JsonProperty("occurrence_id")  int occurrenceId
){

}