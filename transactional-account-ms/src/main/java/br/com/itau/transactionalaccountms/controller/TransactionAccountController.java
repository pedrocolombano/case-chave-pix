package br.com.itau.transactionalaccountms.controller;

import br.com.itau.transactionalaccountms.model.dto.request.TransactionAccountRegistrationDto;
import br.com.itau.transactionalaccountms.model.dto.response.TransactionAccountDto;
import br.com.itau.transactionalaccountms.service.TransactionAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transaction-accounts")
@RequiredArgsConstructor
public class TransactionAccountController {

    private final TransactionAccountService transactionAccountService;

    @PostMapping
    public ResponseEntity<TransactionAccountDto> create(@RequestBody TransactionAccountRegistrationDto registrationDto) {
        TransactionAccountDto response = transactionAccountService.create(registrationDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                             .path("/{id}")
                                             .buildAndExpand(response.getId())
                                             .toUri();

        return ResponseEntity.created(uri)
                             .body(response);
    }

}
