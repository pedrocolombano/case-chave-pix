package br.com.itau.userms.service;

import br.com.itau.userms.mapper.UserMapper;
import br.com.itau.userms.model.dto.request.CreateUserDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.model.entity.User;
import br.com.itau.userms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public UserDto insert(final CreateUserDto createUserDto) {
        User user = userMapper.fromDtoToEntity(createUserDto);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return userMapper.fromEntityToDto(user);
    }
}
