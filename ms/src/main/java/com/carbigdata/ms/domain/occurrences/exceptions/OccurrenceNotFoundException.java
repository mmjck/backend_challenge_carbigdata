package com.carbigdata.ms.domain.occurrences.exceptions;

public class OccurrenceNotFoundException extends RuntimeException{
    public OccurrenceNotFoundException(){
        super("Occurrence not found");
    }
}
