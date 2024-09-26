package br.com.itau.accountms.controller;

import br.com.itau.accountms.model.dto.request.AccountRegistrationDto;
import br.com.itau.accountms.model.dto.response.AccountDto;
import br.com.itau.accountms.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> register(@RequestBody AccountRegistrationDto accountRegistrationDto) {
        AccountDto response = accountService.register(accountRegistrationDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
