package br.com.itau.transactionalaccountms.strategy.impl;

import br.com.itau.transactionalaccountms.exception.KeyRegistrationException;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import br.com.itau.transactionalaccountms.strategy.KeyValidationStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class RandomKeyValidationImplTest {

    private final KeyValidationStrategy randomKeyValidation = new RandomKeyValidationImpl();

    @Test
    public void getKeyTypeShouldReturnRandomKeyType() {
        assertEquals(KeyType.RANDOM, randomKeyValidation.getKeyType());
    }

    @Test
    public void validateShouldThrowKeyRegistrationExceptionIfKeySizeIsInvalid() {
        assertThrows(KeyRegistrationException.class, () -> randomKeyValidation.validate("invalidSizeKey"));
    }

    @Test
    public void validateShouldThrowKeyRegistrationExceptionIfKeyWithoutHyphensIsInvalid() {
        String invalidKey = "12345678901@'567890123456789012!";
        assertThrows(KeyRegistrationException.class, () -> randomKeyValidation.validate(invalidKey));
    }

    @Test
    public void validateShouldThrowKeyRegistrationExceptionIfKeyWithHyphensIsInvalid() {
        String invalidKey = "3fa!@8a7-71#b-44%4-a8e1-2ab9b59ee85!";
        assertThrows(KeyRegistrationException.class, () -> randomKeyValidation.validate(invalidKey));
    }

}
