package br.com.itau.transactionalaccountms.service;

import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import br.com.itau.transactionalaccountms.strategy.KeyValidationStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeyValidationService {

    private final Map<KeyType, KeyValidationStrategy> keyValidations = new HashMap<>();

    public KeyValidationService(List<KeyValidationStrategy> keyValidationStrategies) {
        keyValidationStrategies.forEach(strategy -> keyValidations.put(strategy.getKeyType(), strategy));
    }

    public void validateKey(final String key, final KeyType keyType) {
        KeyValidationStrategy strategy = keyValidations.get(keyType);
        strategy.validate(key);
    }
}
