package com.carbigdata.ms.domain.occurrences.exceptions;

public class OccurrenceNotBeModifiedException extends RuntimeException{
    public OccurrenceNotBeModifiedException(){
        super("Occurence cannot be modified once it is finalized");
    }
}
