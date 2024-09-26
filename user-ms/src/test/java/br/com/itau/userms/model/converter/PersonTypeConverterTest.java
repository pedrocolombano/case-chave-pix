package br.com.itau.userms.model.converter;

import br.com.itau.userms.model.enumerated.PersonType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTypeConverterTest {

    private final PersonTypeConverter personTypeConverter = new PersonTypeConverter();

    @Test
    public void convertToDatabaseColumnShouldReturnEnumAbbreviation() {
        String abbreviation = personTypeConverter.convertToDatabaseColumn(PersonType.NATURAL);
        Assertions.assertEquals("F", abbreviation);
    }

    @Test
    public void convertToEntityAttributeShouldReturnAnEnum() {
        PersonType personType = personTypeConverter.convertToEntityAttribute("F");
        Assertions.assertEquals(PersonType.NATURAL, personType);
    }

}
