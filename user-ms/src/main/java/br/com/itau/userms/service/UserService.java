package br.com.itau.userms.service;

import br.com.itau.userms.exception.ResourceNotFoundException;
import br.com.itau.userms.mapper.UserMapper;
import br.com.itau.userms.model.dto.request.UserInsertDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.model.entity.User;
import br.com.itau.userms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public UserDto insert(final UserInsertDto userInsertDto) {
        User user = userMapper.fromDtoToEntity(userInsertDto);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return userMapper.fromEntityToDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto findById(final UUID id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("O usuário não foi encontrado."));

        return userMapper.fromEntityToDto(user);
    }
}
