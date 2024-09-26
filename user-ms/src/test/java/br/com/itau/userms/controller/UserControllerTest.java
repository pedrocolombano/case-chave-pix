package br.com.itau.userms.controller;

import br.com.itau.userms.exception.InvalidEnumConstantException;
import br.com.itau.userms.exception.ResourceNotFoundException;
import br.com.itau.userms.model.dto.request.UserInsertDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.model.enumerated.PersonType;
import br.com.itau.userms.service.UserService;
import br.com.itau.userms.util.UserFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void insertUserShouldCreateAndReturnUserDto() throws Exception {
        UserInsertDto dto = UserFactory.createUserInsertionDto("123456789", "12345678901", "F");
        UserDto createdUser = UserFactory.createUserDto(1L, "123456789", "12345678901", PersonType.NATURAL);

        when(userService.insert(any(UserInsertDto.class))).thenReturn(createdUser);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON)
                                                .content(json)
                        ).andExpect(status().isCreated())
                         .andExpect(jsonPath("$.id")
                                .value(createdUser.getId()))
                         .andExpect(jsonPath("$.document")
                                .value(dto.getDocument()))
                         .andExpect(jsonPath("$.taxId")
                                .value(dto.getTaxId()))
                         .andExpect(jsonPath("$.personType")
                                .value(PersonType.NATURAL.name()));
    }

    @Test
    public void insertShouldThrowUnprocessableEntityStatusWithValidationErrorsWhenUserDataIsNotValid() throws Exception {
        UserInsertDto dto = UserFactory.createUserInsertionDto("12345678911111111", "12345678901", "F");

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(status().isUnprocessableEntity())
                 .andExpect(result -> assertInstanceOf(MethodArgumentNotValidException.class,
                                                       result.getResolvedException()))
                 .andExpect(jsonPath("$.message")
                         .value("Há erros de validação nos dados enviados."))
                 .andExpect(jsonPath("$.errors").isNotEmpty());

    }

    @Test
    public void insertShouldThrowUnprocessableEntityStatusWhenPersonTypeIsInvalid() throws Exception {
        UserInsertDto dto = UserFactory.createUserInsertionDto("123456789", "12345678901", "X");

        String message = String.format("O tipo de pessoa '%s' não é válido. Os valores aceitos são F e J.", dto.getPersonType());
        when(userService.insert(any(UserInsertDto.class))).thenThrow(new InvalidEnumConstantException(message));

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertInstanceOf(InvalidEnumConstantException.class,
                                                      result.getResolvedException()))
                .andExpect(jsonPath("$.message")
                        .value(message));

    }

    @Test
    public void findByIdShouldReturnUserDto() throws Exception {
        Long existentId = 1L;
        UserDto userResponse = UserFactory.createUserDto(existentId, "987654321", "09876543211", PersonType.LEGAL);

        when(userService.findById(existentId)).thenReturn(userResponse);

        mockMvc.perform(get("/users/{id}", existentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                 .andExpect(jsonPath("$.id").value(existentId));
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenUserDoesNotExists() throws Exception {
        Long nonExistentId = 10L;
        String message = "O usuário não foi encontrado.";

        when(userService.findById(nonExistentId)).thenThrow(new ResourceNotFoundException(message));

        mockMvc.perform(get("/users/{id}", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ResourceNotFoundException.class,
                                                      result.getResolvedException()))
                .andExpect(jsonPath("$.message").value(message));
    }

}
