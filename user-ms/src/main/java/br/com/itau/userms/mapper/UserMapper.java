package br.com.itau.userms.mapper;

import br.com.itau.userms.model.dto.request.UserInsertDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.model.entity.User;
import br.com.itau.userms.model.enumerated.PersonType;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDtoToEntity(final UserInsertDto userInsertDto) {
        User user = new User();

        user.setFirstName(userInsertDto.getFirstName());
        user.setSurname(userInsertDto.getSurname());
        user.setEmail(userInsertDto.getEmail());
        user.setDocument(userInsertDto.getDocument());
        user.setTaxId(userInsertDto.getTaxId());
        user.setPersonType(PersonType.getByAbbreviation(userInsertDto.getPersonType()));

        return user;
    }

    public UserDto fromEntityToDto(final User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setDocument(user.getDocument());
        userDto.setTaxId(user.getTaxId());
        userDto.setPersonType(user.getPersonType());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }

}
