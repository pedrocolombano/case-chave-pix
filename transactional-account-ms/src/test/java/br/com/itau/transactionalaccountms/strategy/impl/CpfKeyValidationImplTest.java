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
public class CpfKeyValidationImplTest {

    private final CpfKeyValidationImpl cpfKeyValidation = new CpfKeyValidationImpl();

    @Test
    public void getKeyTypeShouldReturnCpf() {
        assertEquals(KeyType.CPF, cpfKeyValidation.getKeyType());
    }

    @Test
    void shouldThrowExceptionWhenCpfIsInvalidLength() {
        String invalidCpf = "12345678";
        assertThrows(KeyValidationException.class, () -> cpfKeyValidation.validate(invalidCpf));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "00000000000",
            "11111111111",
            "22222222222",
            "33333333333",
            "44444444444",
            "55555555555",
            "66666666666",
            "77777777777",
            "88888888888",
            "99999999999"
    })
    void shouldThrowExceptionWhenCpfIsInInvalidPattern(String invalidCpf) {
        assertThrows(KeyValidationException.class, () -> cpfKeyValidation.validate(invalidCpf));
    }

    @Test
    void shouldThrowExceptionWhenCpfHasInvalidVerifierDigits() {
        String invalidVerifierCpf = "58137208023";

        assertThrows(KeyValidationException.class, () -> cpfKeyValidation.validate(invalidVerifierCpf),
                "Deve lançar exceção quando o CPF possui dígitos verificadores inválidos.");
    }

    @Test
    void shouldNotThrowExceptionWhenCpfIsValid() {
        String validCpf = "58137208046";
        assertDoesNotThrow(() -> cpfKeyValidation.validate(validCpf));
    }

    @Test
    void shouldThrowExceptionWhenCpfContainsInvalidCharacters() {
        String invalidCpf = "1@3.4&6.7+9-09";

        assertThrows(KeyValidationException.class, () -> cpfKeyValidation.validate(invalidCpf));
    }

}
