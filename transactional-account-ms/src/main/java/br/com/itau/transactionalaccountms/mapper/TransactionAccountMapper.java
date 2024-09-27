package br.com.itau.transactionalaccountms.mapper;

import br.com.itau.transactionalaccountms.model.dto.request.TransactionAccountRegistrationDto;
import br.com.itau.transactionalaccountms.model.dto.response.TransactionAccountDto;
import br.com.itau.transactionalaccountms.model.entity.TransactionAccount;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import org.springframework.stereotype.Component;

@Component
public class TransactionAccountMapper {

    public TransactionAccount fromDtoToEntity(TransactionAccountRegistrationDto dto) {
        TransactionAccount entity = new TransactionAccount();

        entity.setKey(dto.getKey());
        entity.setKeyType(KeyType.getByName(dto.getKeyType()));

        return entity;
    }

    public TransactionAccountDto fromEntityToDto(final TransactionAccount entity) {
        TransactionAccountDto dto = new TransactionAccountDto();

        dto.setId(entity.getId());

        return dto;
    }

}
