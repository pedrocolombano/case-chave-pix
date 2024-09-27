package br.com.itau.transactionalaccountms.repository;

import br.com.itau.transactionalaccountms.model.entity.TransactionAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionAccountRepository extends JpaRepository<TransactionAccount, UUID> {
}
