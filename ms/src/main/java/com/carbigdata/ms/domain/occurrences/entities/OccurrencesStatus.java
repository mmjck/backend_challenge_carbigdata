package com.carbigdata.ms.domain.occurrences.entities;

public enum OccurrencesStatus {

    FINISHED(1, "finished"),
    ACTIVE(2, "active");

    private int value;
    private String message;

    private OccurrencesStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return this.value;
    }

    public String getMessate(){
        return this.message;
    }

}
