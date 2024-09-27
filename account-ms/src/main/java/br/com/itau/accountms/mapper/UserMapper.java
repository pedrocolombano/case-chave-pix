package br.com.itau.accountms.mapper;

import br.com.itau.accountms.model.dto.response.UserDto;
import br.com.itau.accountms.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDtoToEntity(final UserDto dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setSurname(dto.getSurname());
        user.setPersonType(dto.getPersonType());
        user.setTaxId(dto.getTaxId());

        return user;
    }

}
