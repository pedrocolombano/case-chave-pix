package br.com.itau.transactionalaccountms.model.enumerated;

import br.com.itau.transactionalaccountms.exception.InvalidEnumConstantException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KeyTypeTest {

    @Test
    void shouldReturnEnumValueWhenValidNameProvided() {
        assertEquals(KeyType.CPF, KeyType.getByName("CPF"));
        assertEquals(KeyType.CNPJ, KeyType.getByName("CNPJ"));
        assertEquals(KeyType.EMAIL, KeyType.getByName("EMAIL"));
        assertEquals(KeyType.PHONE, KeyType.getByName("PHONE"));
        assertEquals(KeyType.RANDOM, KeyType.getByName("RANDOM"));
    }

    @Test
    void shouldThrowExceptionWhenInvalidNameIsProvided() {
        String invalidName = "INVALID_KEY";
        assertThrows(InvalidEnumConstantException.class, () -> KeyType.getByName(invalidName));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(InvalidEnumConstantException.class, () -> KeyType.getByName(null));
    }

}
