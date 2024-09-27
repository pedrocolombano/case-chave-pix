package br.com.itau.transactionalaccountms.mapper;

import br.com.itau.transactionalaccountms.model.dto.response.AccountDto;
import br.com.itau.transactionalaccountms.model.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account fromDtoToEntity(final AccountDto dto) {
        Account account = new Account();

        account.setId(dto.getId());
        account.setAccountType(dto.getAccountType());
        account.setNumber(dto.getNumber());
        account.setBranch(dto.getBranch());
        account.setHolderName(dto.getHolderFirstName());
        account.setHolderSurname(dto.getHolderSurname());
        account.setHolderType(dto.getHolderPersonType());

        return account;
    }

}
