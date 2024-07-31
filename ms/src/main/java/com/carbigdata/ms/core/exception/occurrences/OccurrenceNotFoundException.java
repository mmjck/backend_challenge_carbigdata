package com.carbigdata.ms.core.exception.occurrences;

public class OccurrenceNotFoundException extends RuntimeException{
    public OccurrenceNotFoundException(){
        super("Occurrence not found");
    }
}
