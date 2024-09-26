package br.com.itau.accountms.service;

import br.com.itau.accountms.client.UserClient;
import br.com.itau.accountms.mapper.UserMapper;
import br.com.itau.accountms.model.dto.request.UserRegistrationDto;
import br.com.itau.accountms.model.dto.response.UserDto;
import br.com.itau.accountms.model.entity.User;
import br.com.itau.accountms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User insert(final UserRegistrationDto userRegistrationDto) {
        UserDto userDto = userClient.register(userRegistrationDto);

        User newUser = userMapper.fromDtoToEntity(userDto);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(newUser);

        return newUser;
    }


}
