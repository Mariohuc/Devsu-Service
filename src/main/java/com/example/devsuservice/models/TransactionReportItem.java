package com.example.devsuservice.models;

import com.example.devsuservice.models.enums.AccountType;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SqlResultSetMapping;

import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
@SqlResultSetMapping(name = "TransactionReportItem", classes = @ConstructorResult(targetClass = TransactionReportItem.class, columns = {
        @ColumnResult(name = "date", type = Date.class),
        @ColumnResult(name = "client", type = String.class),
        @ColumnResult(name = "accountNumber", type = Integer.class),
        @ColumnResult(name = "type", type = AccountType.class),
        @ColumnResult(name = "initialBalance", type = BigDecimal.class),
        @ColumnResult(name = "status", type = Boolean.class),
        @ColumnResult(name = "transaction", type = BigDecimal.class),
        @ColumnResult(name = "availableBalance", type = BigDecimal.class)
}))
public class TransactionReportItem {
    private Date date;
    private String client;
    private Integer accountNumber;
    private AccountType type;
    private BigDecimal initialBalance;
    private Boolean status;
    private BigDecimal transaction;
    private BigDecimal availableBalance;

    public TransactionReportItem(Date date, String client, Integer accountNumber, AccountType type, BigDecimal initialBalance, Boolean status, BigDecimal transaction, BigDecimal availableBalance) {
        this.date = date;
        this.client = client;
        this.accountNumber = accountNumber;
        this.type = type;
        this.initialBalance = initialBalance;
        this.status = status;
        this.transaction = transaction;
        this.availableBalance = availableBalance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigDecimal getTransaction() {
        return transaction;
    }

    public void setTransaction(BigDecimal transaction) {
        this.transaction = transaction;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }
}
