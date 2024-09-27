package br.com.itau.transactionalaccountms.model.enumerated;

import br.com.itau.transactionalaccountms.exception.InvalidEnumConstantException;

import java.util.List;

public enum KeyType {

    CPF,
    CNPJ,
    EMAIL,
    PHONE,
    RANDOM;

    public static KeyType getByName(final String name) {
        try {
            return KeyType.valueOf(name);
        } catch (IllegalArgumentException e) {
            String message = String.format("O tipo da chave informada é inválido. Valores aceitos: %s",
                                           List.of(values()));
            throw new InvalidEnumConstantException(message);
        }
    }

}
