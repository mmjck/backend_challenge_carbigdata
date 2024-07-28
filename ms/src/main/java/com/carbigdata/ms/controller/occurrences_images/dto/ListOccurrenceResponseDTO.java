package com.carbigdata.ms.controller.occurrences_images.dto;
import java.time.LocalDateTime;

public record ListOccurrenceResponseDTO
    (   
        String name,
        String cpf,

        String urlImage,

        String city,
        String state,
        String district,
        String zipCode,
        String street,
        
        LocalDateTime createdAt


    ) {
    
}
