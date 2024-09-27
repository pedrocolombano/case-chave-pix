package br.com.itau.transactionalaccountms.service;

import br.com.itau.transactionalaccountms.client.AccountClient;
import br.com.itau.transactionalaccountms.mapper.AccountMapper;
import br.com.itau.transactionalaccountms.model.dto.response.AccountDto;
import br.com.itau.transactionalaccountms.model.entity.Account;
import br.com.itau.transactionalaccountms.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountClient accountClient;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public Account getAccountById(final UUID id) {
        return accountRepository.findById(id)
                                .orElseGet(() -> create(id));
    }

    private Account create(final UUID id) {
        AccountDto accountDto = accountClient.findById(id);

        Account account = accountMapper.fromDtoToEntity(accountDto);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        accountRepository.saveAndFlush(account);

        return account;
    }


}
