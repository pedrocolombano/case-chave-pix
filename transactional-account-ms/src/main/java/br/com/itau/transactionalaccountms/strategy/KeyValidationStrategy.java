package br.com.itau.transactionalaccountms.strategy;

import br.com.itau.transactionalaccountms.model.enumerated.KeyType;

public interface KeyValidationStrategy {

    KeyType getKeyType();
    void validate(String key);

}
