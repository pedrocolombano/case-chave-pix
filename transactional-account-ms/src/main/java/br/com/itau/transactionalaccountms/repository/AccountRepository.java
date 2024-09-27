package br.com.itau.transactionalaccountms.repository;

import br.com.itau.transactionalaccountms.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
