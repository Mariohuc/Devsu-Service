package com.example.devsuservice.models.enums;

public enum Gender {
    M("M"),
    F("F");

    private String code;

    private Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
