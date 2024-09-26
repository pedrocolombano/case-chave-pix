package br.com.itau.userms.model.enumerated;

import br.com.itau.userms.exception.InvalidEnumConstantException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTypeTest {

    @Test
    public void getByAbbreviationShouldReturnAnEnumValueIfAbbreviationExists() {
        assertDoesNotThrow(() -> PersonType.getByAbbreviation("F"));
    }

    @Test
    public void getByAbbreviationShouldThrowInvalidEnumConstantExceptionIfAbbreviationDoesNotExists() {
        assertThrows(InvalidEnumConstantException.class,
                     () -> PersonType.getByAbbreviation("A"));
    }

}
