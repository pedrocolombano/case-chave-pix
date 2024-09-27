package br.com.itau.accountms.mapper;

import br.com.itau.accountms.model.dto.request.AccountRegistrationDto;
import br.com.itau.accountms.model.dto.response.AccountDto;
import br.com.itau.accountms.model.entity.Account;
import br.com.itau.accountms.model.entity.User;
import br.com.itau.accountms.model.enumerated.AccountType;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account fromDtoToEntity(final AccountRegistrationDto accountRegistrationDto) {
        Account account = new Account();

        account.setNumber(accountRegistrationDto.getNumber());
        account.setBranch(accountRegistrationDto.getBranch());
        account.setAccountType(AccountType.getByName(accountRegistrationDto.getAccountType()));

        return account;
    }

    public AccountDto fromEntityToDto(final Account account) {
        AccountDto dto = new AccountDto();

        dto.setId(account.getId());
        dto.setBranch(account.getBranch());
        dto.setNumber(account.getNumber());
        dto.setAccountType(account.getAccountType());
        dto.setCreatedAt(account.getCreatedAt());
        dto.setUpdatedAt(account.getUpdatedAt());

        User accountHolder = account.getHolder();
        dto.setHolderFirstName(accountHolder.getFirstName());
        dto.setHolderSurname(accountHolder.getSurname());
        dto.setTaxId(accountHolder.getTaxId());
        dto.setHolderId(accountHolder.getId());

        return dto;
    }

}
