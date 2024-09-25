package br.com.itau.userms.mapper;

import br.com.itau.userms.model.dto.request.CreateUserDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDtoToEntity(final CreateUserDto createUserDto) {
        User user = new User();

        user.setFirstName(createUserDto.getFirstName());
        user.setSurname(createUserDto.getSurname());
        user.setEmail(createUserDto.getEmail());
        user.setDocument(createUserDto.getDocument());
        user.setTaxId(createUserDto.getTaxId());
        user.setPersonType(createUserDto.getPersonType());

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
