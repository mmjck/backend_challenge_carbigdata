package com.carbigdata.ms.core.exception.occurrences;

public class OccurrenceNotBeModifiedException extends RuntimeException{
    public OccurrenceNotBeModifiedException(){
        super("Occurence cannot be modified once it is finalized");
    }
}
