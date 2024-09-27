package br.com.itau.transactionalaccountms.mapper;

import br.com.itau.transactionalaccountms.model.dto.response.TransactionAccountDto;
import br.com.itau.transactionalaccountms.model.entity.TransactionAccount;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

        TransactionAccountDto transactionAccount = transactionAccountMapper.fromEntityToDto(entity);

        assertEquals(id, transactionAccount.getId());
    }

}