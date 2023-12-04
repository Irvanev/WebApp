package com.example.webapp.constants;

public enum TransmissionEnum {
    MANUAL (0),
    AUTOMATIC (1);
    private int number;

    TransmissionEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}