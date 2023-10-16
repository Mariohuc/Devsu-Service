package com.example.devsuservice.dto;

import com.example.devsuservice.models.enums.Gender;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class ClientRequest {
    @Pattern(regexp = "^(?!\\s*$).+", message = "Nombre inválido: Esta vacío")
    private String name;
    private Integer age;
    private Gender gender;
    private Integer identificationCardNumber;
    private String address;
    private String phone;
    @Min(value = 1000, message = "Contraseña inválida: Debe tener 4 dígitos")
    @Max(value = 9999, message = "Contraseña inválida: Debe tener 4 dígitos")
    private Integer statusPassword;
    private Boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIdentificationCardNumber() {
        return identificationCardNumber;
    }

    public void setIdentificationCardNumber(Integer identificationCardNumber) {
        this.identificationCardNumber = identificationCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
