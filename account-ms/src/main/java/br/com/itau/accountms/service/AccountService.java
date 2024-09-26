package br.com.itau.accountms.service;

import br.com.itau.accountms.mapper.AccountMapper;
import br.com.itau.accountms.model.dto.request.AccountRegistrationDto;
import br.com.itau.accountms.model.dto.response.AccountDto;
import br.com.itau.accountms.model.entity.Account;
import br.com.itau.accountms.model.entity.User;
import br.com.itau.accountms.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserService userService;

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Transactional
    public AccountDto register(final AccountRegistrationDto accountRegistrationDto) {
        User accountHolder = userService.insert(accountRegistrationDto.getHolder());
        Account account = accountMapper.fromDtoToEntity(accountRegistrationDto);

        account.setHolder(accountHolder);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        accountRepository.save(account);
        return accountMapper.fromEntityToDto(account);
    }

}
