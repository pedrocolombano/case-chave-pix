package br.com.itau.transactionalaccountms.mapper;

import br.com.itau.transactionalaccountms.model.dto.response.TransactionAccountDto;
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

        return dto;
    }

}
