package com.example.devsuservice.models;

import com.example.devsuservice.models.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "acc_number", nullable = false, unique = true)
    private Integer accountNumber;
    @Column(nullable = false)
    @NotNull(message = "Tipo de cuenta inválida: Debe ingresar un tipo")
    private AccountType type;
    @Min(value = 0, message = "Saldo inválido: Debe ser positivo")
    @Column(precision=20, scale=2)
    private BigDecimal balance;

    private Boolean status = true;
    @NotNull(message = "ID cliente inválido: Debe ingresar uno")
    @Column(name = "client_id", nullable = false)
    private Integer clientId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_number", referencedColumnName = "acc_number")
    @JsonIgnore
    private final Set<Transaction> transactions = new HashSet<>();

    @PrePersist
    public void genAccountNumber() {
        this.setAccountNumber((int) Math.floor(Math.random() * 900000) + 100000);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }
}
