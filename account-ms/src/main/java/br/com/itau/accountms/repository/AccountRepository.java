package br.com.itau.accountms.repository;

import br.com.itau.accountms.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByHolderTaxId(String taxId);

}
