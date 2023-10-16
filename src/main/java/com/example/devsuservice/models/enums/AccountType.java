package com.example.devsuservice.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccountType {
    @JsonProperty("Ahorros")
    AHORROS("Ahorros"),
    @JsonProperty("Corriente")
    CORRIENTE("Corriente");

    private String code;

    private AccountType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
