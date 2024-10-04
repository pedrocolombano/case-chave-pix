package br.com.itau.transactionalaccountms.mapper;

import br.com.itau.transactionalaccountms.model.dto.response.TransactionAccountDto;
import br.com.itau.transactionalaccountms.model.entity.Account;
import br.com.itau.transactionalaccountms.model.entity.TransactionAccount;
import br.com.itau.transactionalaccountms.model.enumerated.AccountType;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionAccountMapperTest {

    private final TransactionAccountMapper transactionAccountMapper = new TransactionAccountMapper();

    @Test
    public void createShouldReturnATransactionAccount() {
        String key = "12345678901";
        KeyType keyType = KeyType.CPF;
        TransactionAccount transactionAccount = transactionAccountMapper.create(key, keyType);

        assertEquals(key, transactionAccount.getKey());
        assertEquals(keyType, transactionAccount.getKeyType());
    }

    @Test
    public void fromEntityToDtoShouldReturnEntityData() {
        UUID id = UUID.randomUUID();

        TransactionAccount entity = new TransactionAccount();

        entity.setId(id);
        entity.setKey("email@gmail.com");
        entity.setKeyType(KeyType.EMAIL);
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setDeactivatedAt(LocalDateTime.now());

        Account account = new Account();
        account.setId(id);
        account.setAccountType(AccountType.CURRENT);
        account.setBranch(1);
        account.setNumber(123);
        account.setHolderName("Alex");
        account.setHolderSurname("Green");

        entity.setAccount(account);

        TransactionAccountDto transactionAccount = transactionAccountMapper.fromEntityToDto(entity);

        assertEquals(id, transactionAccount.getId());
        assertEquals(entity.getKey(), transactionAccount.getKey());
        assertEquals(entity.getKeyType(), transactionAccount.getKeyType());
        assertEquals(entity.getCreatedAt(), transactionAccount.getCreatedAt());
        assertEquals(entity.getUpdatedAt(), transactionAccount.getUpdatedAt());
        assertEquals(entity.getDeactivatedAt(), transactionAccount.getDeactivatedAt());
        assertEquals(account.getAccountType(), transactionAccount.getAccountType());
        assertEquals(account.getBranch(), transactionAccount.getBranch());
        assertEquals(account.getNumber(), transactionAccount.getNumber());
        assertEquals(account.getHolderName(), transactionAccount.getHolderName());
        assertEquals(account.getHolderSurname(), transactionAccount.getHolderSurname());
    }

}