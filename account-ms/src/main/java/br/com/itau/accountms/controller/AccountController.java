package br.com.itau.accountms.controller;

import br.com.itau.accountms.model.dto.request.AccountRegistrationDto;
import br.com.itau.accountms.model.dto.response.AccountDto;
import br.com.itau.accountms.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> register(@RequestBody @Valid AccountRegistrationDto accountRegistrationDto) {
        AccountDto response = accountService.register(accountRegistrationDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                             .path("/{id}")
                                             .buildAndExpand(response.getId())
                                             .toUri();

        return ResponseEntity.created(uri)
                             .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findById(@PathVariable Long id) {
        AccountDto response = accountService.findById(id);
        return ResponseEntity.ok(response);
    }

}
