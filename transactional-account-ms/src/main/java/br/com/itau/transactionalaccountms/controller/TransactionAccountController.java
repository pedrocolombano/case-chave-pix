package br.com.itau.transactionalaccountms.controller;

import br.com.itau.transactionalaccountms.model.dto.request.TransactionAccountRegistrationDto;
import br.com.itau.transactionalaccountms.model.dto.request.param.TransactionAccountSearchDto;
import br.com.itau.transactionalaccountms.model.dto.response.TransactionAccountDto;
import br.com.itau.transactionalaccountms.repository.view.TransactionAccountView;
import br.com.itau.transactionalaccountms.service.TransactionAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction-accounts")
@RequiredArgsConstructor
public class TransactionAccountController {

    private final TransactionAccountService transactionAccountService;

    @PostMapping
    public ResponseEntity<TransactionAccountDto> create(@RequestBody @Valid TransactionAccountRegistrationDto registrationDto) {
        TransactionAccountDto response = transactionAccountService.create(registrationDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                             .path("/{id}")
                                             .buildAndExpand(response.getId())
                                             .toUri();

        return ResponseEntity.created(uri)
                             .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionAccountView>> getAll(TransactionAccountSearchDto searchDto) {
        List<TransactionAccountView> response = transactionAccountService.findAll(searchDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionAccountDto> inactivateKeyById(@PathVariable UUID id) {
        TransactionAccountDto response = transactionAccountService.inactiveKey(id);
        return ResponseEntity.ok(response);
    }

}
