package br.com.itau.userms.service;

import br.com.itau.userms.mapper.UserMapper;
import br.com.itau.userms.model.dto.request.UserInsertDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.model.entity.User;
import br.com.itau.userms.model.enumerated.PersonType;
import br.com.itau.userms.repository.UserRepository;
import br.com.itau.userms.util.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private final ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    @Test
    public void insertShouldSaveUserAndReturnDto() {
        UserInsertDto insertDto = UserFactory.createUserInsertionDto("123456789", "12345678901", "F");
        User entity = UserFactory.createUser(1L, "123456789", "12345678901", PersonType.NATURAL);
        UserDto userDto = UserFactory.createUserDto(1L, "123456789", "12345678901", PersonType.NATURAL);

        when(userMapper.fromDtoToEntity(insertDto)).thenReturn(entity);
        when(userMapper.fromEntityToDto(entity)).thenReturn(userDto);

        userService.insert(insertDto);

        verify(userMapper, Mockito.times(1)).fromDtoToEntity(insertDto);
        verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any());
        verify(userMapper, Mockito.times(1)).fromEntityToDto(ArgumentMatchers.any());

        verify(userRepository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();

        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
    }

}
