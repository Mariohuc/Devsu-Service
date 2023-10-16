package com.example.devsuservice.models;

import com.example.devsuservice.models.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "txn_date")
    private LocalDateTime transactionDate;

    private AccountType type;
    @Column(name = "init_balance", precision=20, scale=2, nullable = false)
    private BigDecimal initialBalance;
    @Column(name = "post_balance", precision=20, scale=2, nullable = false)
    private BigDecimal postBalance;
    @Column(precision=20, scale=2, nullable = false)
    @NotNull(message = "Valor inválido: Debe ingresar un valor")
    private BigDecimal value;
    @Column(name = "acc_number", nullable = false)
    @NotNull(message = "Nro. de cuenta inválida: Debe ingresar un nro. de cuenta")
    private Integer accountNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getPostBalance() {
        return postBalance;
    }

    public void setPostBalance(BigDecimal postBalance) {
        this.postBalance = postBalance;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
}
