package com.example.devsuservice.dto;

import com.example.devsuservice.models.enums.AccountType;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public class AccountRequest {
    private AccountType type;

    @Min(value = 0, message = "Saldo inválido: Debe ser positivo")
    private BigDecimal balance;
    private Boolean status;

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
