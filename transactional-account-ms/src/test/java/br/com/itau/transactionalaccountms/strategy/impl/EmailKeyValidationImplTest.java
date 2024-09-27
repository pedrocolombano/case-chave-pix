package br.com.itau.transactionalaccountms.strategy.impl;

import br.com.itau.transactionalaccountms.exception.KeyRegistrationException;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class EmailKeyValidationImplTest {

    private final EmailKeyValidationImpl emailKeyValidation = new EmailKeyValidationImpl();

    @Test
    public void getKeyTypeShouldReturnEmail() {
        assertEquals(KeyType.EMAIL, emailKeyValidation.getKeyType());
    }

    @Test
    public void validateShouldThrowKeyRegistrationExceptionWhenEmailDoesNotContainsAtCharacter() {
        assertThrows(KeyRegistrationException.class, () ->
                            emailKeyValidation.validate("pedromaiacolombanogmail.com"));
    }

    @Test
    public void validateShouldDoNothingWhenEmailContainsAtCharacter() {
        assertDoesNotThrow(() -> emailKeyValidation.validate("pedromaiacolombano@gmail.com"));
    }

}