package com.carbigdata.ms.repositories.occurrences.jpa.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

record Images(
        String hash,
        String path) {
}

public record OccurrencesListDTO(
        String id,
        String state,
        String createdAt,
        String status,
        String cpf,
        String district,
        String city,
        String fullName,
        String zipCode,
        Integer clientId,
        List<Images> images) {

    public OccurrencesListDTO(
            String id,
            String status,
            String createdAt,
            String json, // raw json of images
            String state,
            String city,
            String zipCode,

            String district,
            String fullName,
            String cpf,
            Integer clientId
            ) {
        this(
            
                id,
                state,
                createdAt,
                status,
                cpf,
                district,
                city,
                fullName,
                zipCode,
                clientId,
                parseImagesJson(json));
    }

    private static List<Images> parseImagesJson(String stringToJson) {
        ObjectMapper mapper = new ObjectMapper();

        if(stringToJson.equals("[{}]")){
            return new ArrayList<>();
        }

        try {
        
            List<Images> images = mapper.readValue(stringToJson, new TypeReference<List<Images>>(){});

            return images;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse phone numbers JSON", e);
        }
    }

}
