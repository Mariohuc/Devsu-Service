package com.example.devsuservice.models.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;
@Converter(autoApply = true)
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {

    @Override
    public String convertToDatabaseColumn(AccountType accType) {
        if (accType == null) {
            return null;
        }
        return accType.getCode();
    }

    @Override
    public AccountType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(AccountType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
