package br.com.itau.transactionalaccountms.strategy.impl;

import br.com.itau.transactionalaccountms.exception.KeyValidationException;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CnpjKeyValidationImplTest {

    private final CnpjKeyValidationImpl cnpjKeyValidation = new CnpjKeyValidationImpl();

    @Test
    public void getKeyTypeShouldReturnCnpj() {
        assertEquals(KeyType.CNPJ, cnpjKeyValidation.getKeyType());
    }

    @Test
    void shouldThrowExceptionWhenCnpjHasInvalidLength() {
        String invalidCnpj = "123456789";
        assertThrows(KeyValidationException.class, () -> cnpjKeyValidation.validate(invalidCnpj));
    }

    @ParameterizedTest
    @ValueSource(strings = {
                            "00000000000000",
                            "11111111111111",
                            "22222222222222",
                            "33333333333333",
                            "44444444444444",
                            "55555555555555",
                            "66666666666666",
                            "77777777777777",
                            "88888888888888",
                            "99999999999999"
    })
    void shouldThrowExceptionWhenCnpjIsInvalidPattern(String invalidCnpj) {
        assertThrows(KeyValidationException.class, () -> cnpjKeyValidation.validate(invalidCnpj));
    }

    @Test
    void shouldThrowExceptionWhenCnpjHasInvalidVerifierDigits() {
        String invalidVerifierCnpj = "75944206000138";
        assertThrows(KeyValidationException.class, () -> cnpjKeyValidation.validate(invalidVerifierCnpj));
    }

    @Test
    void shouldNotThrowExceptionWhenCnpjIsValid() {
        String validCnpj = "75944206000179";
        assertDoesNotThrow(() -> cnpjKeyValidation.validate(validCnpj));
    }

    @Test
    void shouldThrowExceptionWhenCnpjContainsInvalidCharacters() {
        String invalidCnpj = "!2.3B5.678/0001-95";
        assertThrows(KeyValidationException.class, () -> cnpjKeyValidation.validate(invalidCnpj));
    }

}
