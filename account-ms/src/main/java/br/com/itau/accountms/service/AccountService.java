package br.com.itau.accountms.service;

import br.com.itau.accountms.exception.AccountException;
import br.com.itau.accountms.exception.ResourceNotFoundException;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserService userService;

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Transactional
    public AccountDto register(final AccountRegistrationDto accountRegistrationDto) {
        validateIfAccountExists(accountRegistrationDto);

        User accountHolder = userService.insert(accountRegistrationDto.getHolder());
        Account account = accountMapper.fromDtoToEntity(accountRegistrationDto);

        account.setHolder(accountHolder);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        accountRepository.save(account);
        return accountMapper.fromEntityToDto(account);
    }

    private void validateIfAccountExists(AccountRegistrationDto accountRegistrationDto) {
        boolean alreadyHasAccount = accountRepository.existsByHolderTaxId(accountRegistrationDto.getHolder().getTaxId());
        if (alreadyHasAccount) {
            throw new AccountException("O CPF/CNPJ informado não está disponível.");
        }
    }

    @Transactional(readOnly = true)
    public AccountDto findById(final UUID id) {
        Account account = accountRepository.findById(id)
                                           .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada."));

        return accountMapper.fromEntityToDto(account);
    }

}
