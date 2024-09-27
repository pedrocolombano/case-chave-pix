package br.com.itau.transactionalaccountms.mapper;

import br.com.itau.transactionalaccountms.model.dto.response.AccountDto;
import br.com.itau.transactionalaccountms.model.entity.Account;
import br.com.itau.transactionalaccountms.model.enumerated.AccountType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AccountMapperTest {

    private final AccountMapper accountMapper = new AccountMapper();

    @Test
    public void fromDtoToEntityShouldConvertDtoDataDtoEntity() {
        AccountDto dto = new AccountDto();

        dto.setId(UUID.randomUUID());
        dto.setAccountType(AccountType.CURRENT);
        dto.setBranch(1);
        dto.setNumber(1234);
        dto.setHolderFirstName("Pedro");
        dto.setHolderSurname("Colombano");

        Account account = accountMapper.fromDtoToEntity(dto);

        assertEquals(dto.getId(), account.getId());
        assertEquals(dto.getAccountType(), account.getAccountType());
        assertEquals(dto.getBranch(), account.getBranch());
        assertEquals(dto.getNumber(), account.getNumber());
        assertEquals(dto.getHolderFirstName(), account.getHolderName());
        assertEquals(dto.getHolderSurname(), account.getHolderSurname());
    }

}