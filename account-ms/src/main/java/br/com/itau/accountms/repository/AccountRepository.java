package br.com.itau.accountms.repository;

import br.com.itau.accountms.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    boolean existsByHolderTaxId(String taxId);

}
