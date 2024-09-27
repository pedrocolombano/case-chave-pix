package br.com.itau.transactionalaccountms.model.enumerated;

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
            throw new RuntimeException(e);
        }
    }

}
