package br.com.itau.transactionalaccountms.mapper;

import br.com.itau.transactionalaccountms.model.dto.response.TransactionAccountDto;
import br.com.itau.transactionalaccountms.model.entity.Account;
import br.com.itau.transactionalaccountms.model.entity.TransactionAccount;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import org.springframework.stereotype.Component;

@Component
public class TransactionAccountMapper {

    public TransactionAccount create(final String key, final KeyType keyType) {
        TransactionAccount entity = new TransactionAccount();

        entity.setKey(key);
        entity.setKeyType(keyType);

        return entity;
    }

    public TransactionAccountDto fromEntityToDto(final TransactionAccount entity) {
        TransactionAccountDto dto = new TransactionAccountDto();

        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setKeyType(entity.getKeyType());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setDeactivatedAt(entity.getDeactivatedAt());

        Account account = entity.getAccount();
        dto.setBranch(account.getBranch());
        dto.setNumber(account.getNumber());
        dto.setAccountType(account.getAccountType());
        dto.setHolderName(account.getHolderName());
        dto.setHolderSurname(account.getHolderSurname());

        return dto;
    }

}
