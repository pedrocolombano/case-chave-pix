package br.com.itau.userms.model.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonType {

    LEGAL("J"),
    NATURAL("F");

    private final String abbreviation;

    public static PersonType getByAbbreviation(String abbreviation) {
        for (PersonType personType : values()) {
            if (personType.getAbbreviation().equalsIgnoreCase(abbreviation)) {
                return personType;
            }
        }
        String error = String.format("O tipo de pessoa '%s' não é válido. Os valores aceitos são F e J.", abbreviation);
        throw new IllegalArgumentException(error);
    }

}
