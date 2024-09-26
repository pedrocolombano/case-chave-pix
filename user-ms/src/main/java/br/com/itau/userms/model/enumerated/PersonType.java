package br.com.itau.userms.model.enumerated;

import br.com.itau.userms.exception.InvalidEnumConstantException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum PersonType {

    LEGAL,
    NATURAL;

    public static PersonType getByName(String name) {
        try {
            return PersonType.valueOf(name);
        } catch (IllegalArgumentException e) {
            String error = String.format("O tipo de pessoa '%s' não é válido. Os valores aceitos são %s.", name,
                                                                                                           List.of(values()));
            throw new InvalidEnumConstantException(error);
        }
    }

}
