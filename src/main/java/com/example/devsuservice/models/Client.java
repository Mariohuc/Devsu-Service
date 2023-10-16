package com.example.devsuservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends Person {
    @Column(name = "client_id")
    private Integer clientId;
    @Column(name = "status_password", precision = 4, nullable = false)
    @Min(value = 1000, message = "Contraseña inválida: Debe tener 4 dígitos")
    @Max(value = 9999, message = "Contraseña inválida: Debe tener 4 dígitos")
    @NotNull(message = "Contraseña inválida: Es nula")
    private Integer statusPassword;

    private Boolean status = true;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    @JsonIgnore
    private final Set<Account> accounts = new HashSet<>();

    @PrePersist
    public void genClientId() {
        /*
            JPA only mandates support for @GeneratedValue on @Id fields,
            so GenericGenerator would be ignored if used on the clientId as a simple column (it's not a @ID field),
         */
        this.setClientId((int) Math.floor(Math.random() * 90000) + 10000);
    }
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getStatusPassword() {
        return statusPassword;
    }

    public void setStatusPassword(Integer statusPassword) {
        this.statusPassword = statusPassword;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
}
