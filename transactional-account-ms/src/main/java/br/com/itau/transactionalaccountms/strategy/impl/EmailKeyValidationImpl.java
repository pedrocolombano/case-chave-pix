package br.com.itau.transactionalaccountms.strategy.impl;

import br.com.itau.transactionalaccountms.exception.KeyRegistrationException;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import br.com.itau.transactionalaccountms.strategy.KeyValidationStrategy;
import org.springframework.stereotype.Service;

@Service
public class EmailKeyValidationImpl implements KeyValidationStrategy {

    @Override
    public KeyType getKeyType() {
        return KeyType.EMAIL;
    }

    @Override
    public void validate(String key) {
        if (!key.contains("@")) {
            throw new KeyRegistrationException("O e-mail informado possui um formato inválido.");
        }
    }
}
