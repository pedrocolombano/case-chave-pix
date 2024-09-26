package br.com.itau.userms.mapper;

import br.com.itau.userms.util.UserFactory;
import br.com.itau.userms.model.dto.request.UserInsertDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.model.entity.User;
import br.com.itau.userms.model.enumerated.PersonType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
public class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    public void fromDtoToEntityShouldConvertDtoDataToEntity() {
        UserInsertDto requestDto = UserFactory.createUserInsertionDto("123456789",
                                                                      "12345678901234",
                                                                      "J");

        User userDto = userMapper.fromDtoToEntity(requestDto);

        Assertions.assertEquals(requestDto.getFirstName(), userDto.getFirstName());
        Assertions.assertEquals(requestDto.getSurname(), userDto.getSurname());
        Assertions.assertEquals(requestDto.getEmail(), userDto.getEmail());
        Assertions.assertEquals(requestDto.getDocument(), userDto.getDocument());
        Assertions.assertEquals(requestDto.getPersonType(), userDto.getPersonType().getAbbreviation());
        Assertions.assertEquals(requestDto.getTaxId(), userDto.getTaxId());
    }

    @Test
    public void fromEntityToDtoShouldConvertEntityDataToDto() {
        User user = UserFactory.createUser(1L,
                                           "123456789",
                                           "12345678901",
                                           PersonType.NATURAL,
                                           LocalDateTime.now(),
                                           LocalDateTime.now());

        UserDto userDto = userMapper.fromEntityToDto(user);

        Assertions.assertEquals(user.getId(), userDto.getId());
        Assertions.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assertions.assertEquals(user.getSurname(), userDto.getSurname());
        Assertions.assertEquals(user.getEmail(), userDto.getEmail());
        Assertions.assertEquals(user.getDocument(), userDto.getDocument());
        Assertions.assertEquals(user.getPersonType(), userDto.getPersonType());
        Assertions.assertEquals(user.getTaxId(), userDto.getTaxId());
        Assertions.assertEquals(user.getCreatedAt(), userDto.getCreatedAt());
        Assertions.assertEquals(user.getUpdatedAt(), userDto.getUpdatedAt());
    }

}
