package br.com.itau.userms.model.converter;

import br.com.itau.userms.model.enumerated.PersonType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PersonTypeConverter implements AttributeConverter<PersonType, String> {

    @Override
    public String convertToDatabaseColumn(PersonType personType) {
        return personType.getAbbreviation();
    }

    @Override
    public PersonType convertToEntityAttribute(String abbreviation) {
        return PersonType.getByAbbreviation(abbreviation);
    }

}
