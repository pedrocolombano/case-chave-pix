package br.com.itau.transactionalaccountms.model.dto.response;

import br.com.itau.transactionalaccountms.model.enumerated.AccountType;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionAccountDto {

    private UUID id;
    private String key;
    private KeyType keyType;
    private Integer branch;
    private Integer number;
    private AccountType accountType;
    private String holderName;
    private String holderSurname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deactivatedAt;


}
