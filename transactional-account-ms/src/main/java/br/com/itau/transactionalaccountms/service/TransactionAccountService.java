package br.com.itau.transactionalaccountms.service;

import br.com.itau.transactionalaccountms.exception.KeyRegistrationException;
import br.com.itau.transactionalaccountms.mapper.TransactionAccountMapper;
import br.com.itau.transactionalaccountms.model.dto.request.TransactionAccountRegistrationDto;
import br.com.itau.transactionalaccountms.model.dto.response.TransactionAccountDto;
import br.com.itau.transactionalaccountms.model.entity.Account;
import br.com.itau.transactionalaccountms.model.entity.TransactionAccount;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import br.com.itau.transactionalaccountms.repository.TransactionAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionAccountService {

    private final AccountService accountService;
    private final KeyValidationService keyValidationService;

    private final TransactionAccountMapper transactionAccountMapper;
    private final TransactionAccountRepository transactionAccountRepository;

    @Transactional
    public TransactionAccountDto create(final TransactionAccountRegistrationDto registrationDto) {
        KeyType keyType = KeyType.getByName(registrationDto.getKeyType());

        keyValidationService.validateKey(registrationDto.getKey(), keyType);
        validateIfKeyAlreadyExists(registrationDto.getKey());

        Account account = accountService.getAccountById(registrationDto.getAccountId());

        TransactionAccount transactionAccount = transactionAccountMapper.create(registrationDto.getKey(), keyType);

        transactionAccount.setAccount(account);
        transactionAccount.setCreatedAt(LocalDateTime.now());
        transactionAccount.setUpdatedAt(LocalDateTime.now());

        transactionAccountRepository.save(transactionAccount);
        return transactionAccountMapper.fromEntityToDto(transactionAccount);
    }

    private void validateIfKeyAlreadyExists(final String key) {
        boolean isKeyAlreadyRegistered = transactionAccountRepository.existsByKeyIgnoreCase(key);
        if (isKeyAlreadyRegistered) {
            throw new KeyRegistrationException("A chave informada para cadastro já está em uso.");
        }
    }

}
