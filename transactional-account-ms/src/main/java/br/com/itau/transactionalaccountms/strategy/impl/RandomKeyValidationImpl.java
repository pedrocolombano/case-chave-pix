package br.com.itau.transactionalaccountms.strategy.impl;

import br.com.itau.transactionalaccountms.exception.KeyRegistrationException;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import br.com.itau.transactionalaccountms.strategy.KeyValidationStrategy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomKeyValidationImpl implements KeyValidationStrategy {

    public static final String UUID_PATTERN = "(.{8})(.{4})(.{4})(.{4})(.+)";
    public static final String UUID_HYPHENS_REPLACEMENT = "$1-$2-$3-$4-$5";

    @Override
    public KeyType getKeyType() {
        return KeyType.RANDOM;
    }

    @Override
    public void validate(final String key) {
        if (key.length() < 32 || key.length() > 36) {
            throw new KeyRegistrationException("A chave aleatória informada é inválida, deve possuir 32 ou 36 caracteres.");
        }

        try {
            if (key.length() == 32) {
                String formattedKey = key.replaceAll(UUID_PATTERN, UUID_HYPHENS_REPLACEMENT);
                UUID.fromString(formattedKey);
            } else {
                UUID.fromString(key);
            }
        } catch (Exception e) {
            throw new KeyRegistrationException("A chave aleatória informada é inválida.");
        }
    }
}
