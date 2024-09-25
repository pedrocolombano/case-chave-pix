package br.com.itau.userms.model.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

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
        throw new IllegalArgumentException(String.format("'%s' is not a valid person type. The accepted values are %s.",
                                                         abbreviation,
                                                         List.of(values())));
    }

}
