package br.com.itau.userms.controller;

import br.com.itau.userms.model.dto.request.CreateUserDto;
import br.com.itau.userms.model.dto.response.UserDto;
import br.com.itau.userms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> insert(@RequestBody @Valid CreateUserDto createUserDto) {
        UserDto response = userService.insert(createUserDto);
        return ResponseEntity.ok(response);
    }
}
