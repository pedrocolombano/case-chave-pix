package br.com.itau.userms.model.enumerated;

import br.com.itau.userms.exception.InvalidEnumConstantException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTypeTest {

    @Test
    public void getByNameShouldReturnAnEnumValueIfNameExists() {
        assertDoesNotThrow(() -> {
            PersonType personType = PersonType.getByName("NATURAL");
            assertEquals(PersonType.NATURAL, personType);
        });
    }

    @Test
    public void getByAbbreviationShouldThrowInvalidEnumConstantExceptionIfAbbreviationDoesNotExists() {
        assertThrows(InvalidEnumConstantException.class,
                     () -> PersonType.getByName("A"));
    }

}
