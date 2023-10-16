package com.example.devsuservice.models;

import com.example.devsuservice.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Nombre inválido: Esta vacio")
    @NotNull(message = "Nombre inválido: Es nulo")
    private String name;
    private Gender gender;
    @Min(value = 1, message = "Edad inválida: Debe ser mayor igual a 1")
    @Max(value = 100, message = "Edad inválida: Debe ser menor igual a 100")
    private Integer age;
    @Column(name = "id_card_number")
    private Integer identificationCardNumber;
    @Pattern(regexp = "^(?!\\s*$).+", message = "Dirección inválida: Esta vacia")
    @NotNull(message = "Dirección inválida: Es nulo")
    private String address;
    @Pattern(regexp = "^(?!\\s*$).+", message = "Teléfono inválido: Esta vacio")
    @NotNull(message = "Teléfono inválido: Es nulo")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
}
