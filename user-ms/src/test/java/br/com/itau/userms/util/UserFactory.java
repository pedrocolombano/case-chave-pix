package br.com.itau.userms.util;

import br.com.itau.userms.model.dto.request.UserInsertDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.model.entity.User;
import br.com.itau.userms.model.enumerated.PersonType;
import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserFactory {

    private static final Faker FAKER = new Faker();

    public static UserInsertDto createUserInsertionDto(String document, String taxId, String personType) {
        UserInsertDto userDto = new UserInsertDto();

        userDto.setFirstName(FAKER.name().firstName());
        userDto.setSurname(FAKER.name().lastName());
        userDto.setEmail(FAKER.internet().emailAddress());
        userDto.setDocument(document);
        userDto.setTaxId(taxId);
        userDto.setPersonType(personType);

        return userDto;
    }

    public static User createUser(UUID id,
                                  String document,
                                  String taxId,
                                  PersonType personType,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {
        User user = new User();

        user.setId(id);
        user.setFirstName(FAKER.name().firstName());
        user.setSurname(FAKER.name().lastName());
        user.setEmail(FAKER.internet().emailAddress());
        user.setDocument(document);
        user.setTaxId(taxId);
        user.setPersonType(personType);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);

        return user;
    }

    public static User createUser(UUID id,
                                  String document,
                                  String taxId,
                                  PersonType personType) {
        return createUser(id,
                          document,
                          taxId,
                          personType,
                          null,
                          null);
    }

    public static UserDto createUserDto(UUID id,
                                        String document,
                                        String taxId,
                                        PersonType personType) {
        UserDto user = new UserDto();

        user.setId(id);
        user.setFirstName(FAKER.name().firstName());
        user.setSurname(FAKER.name().lastName());
        user.setEmail(FAKER.internet().emailAddress());
        user.setDocument(document);
        user.setTaxId(taxId);
        user.setPersonType(personType);

        return user;
    }

}
