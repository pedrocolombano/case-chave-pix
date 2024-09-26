package br.com.itau.userms.exception.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StandardErrorTest {

    @Test
    public void addErrorShouldAddErrorToList() {
        String field = "Error";
        String message = "Error description";

        StandardError standardError = new StandardError();
        standardError.addError(field, message);

        assertFalse(standardError.getErrors().isEmpty());

        FieldError fieldError = standardError.getErrors().get(0);
        assertEquals(field, fieldError.getField());
        assertEquals(message, fieldError.getMessage());
    }

}
