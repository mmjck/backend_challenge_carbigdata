package com.carbigdata.ms.controller.occurrences_images.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOccurrenceImagesRequestDTO(
   @JsonProperty("occurrence_id")  int occurenceId
){

}