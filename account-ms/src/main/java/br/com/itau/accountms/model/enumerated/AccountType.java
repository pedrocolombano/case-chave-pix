package br.com.itau.accountms.model.enumerated;

import br.com.itau.accountms.exception.InvalidEnumConstantException;

import java.util.List;

public enum AccountType {

    SAVINGS,
    CURRENT;

    public static AccountType getByName(final String name) {
        try {
            return AccountType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            String message = String.format("O tipo de conta '%s' é inválido. Os tipos disponíveis são: %s.", name,
                                                                                                             List.of(values()));
            throw new InvalidEnumConstantException(message);
        }
    }

}
