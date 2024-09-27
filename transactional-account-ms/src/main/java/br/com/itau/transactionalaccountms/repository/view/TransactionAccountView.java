package br.com.itau.transactionalaccountms.repository.view;

import br.com.itau.transactionalaccountms.model.enumerated.AccountType;
import br.com.itau.transactionalaccountms.model.enumerated.KeyType;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionAccountView {

    UUID getId();
    KeyType getKeyType();
    String getKey();
    Integer getBranch();
    Integer getNumber();
    AccountType getAccountType();
    String getHolderName();
    String getHolderSurname();
    LocalDateTime getCreatedAt();
    LocalDateTime getDeactivatedAt();

}
