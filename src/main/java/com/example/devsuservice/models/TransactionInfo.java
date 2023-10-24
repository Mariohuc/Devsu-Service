package com.example.devsuservice.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionInfo {
    Integer getNro();
    LocalDate getDate();

    String getClient();
    Integer getAccountNumber();
    String getType();
    BigDecimal getInitialBalance();
    Boolean getStatus();
    BigDecimal getTransaction();
    BigDecimal getAvailableBalance();
}