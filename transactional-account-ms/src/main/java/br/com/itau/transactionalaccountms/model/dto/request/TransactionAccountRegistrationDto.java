package br.com.itau.transactionalaccountms.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionAccountRegistrationDto {

    private String key;
    private String keyType;
    private UUID accountId;

}
