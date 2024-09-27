package br.com.itau.transactionalaccountms.repository;

import br.com.itau.transactionalaccountms.model.entity.TransactionAccount;
import br.com.itau.transactionalaccountms.repository.view.TransactionAccountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionAccountRepository extends JpaRepository<TransactionAccount, UUID> {

    boolean existsByKeyIgnoreCase(String key);

    @Query(value = "SELECT t.id, "
            + "            t.key_type       AS keyType, "
            + "            t.key, "
            + "            a.branch, "
            + "            a.number, "
            + "            a.account_type   AS accountType, "
            + "            a.holder_name    AS holderName, "
            + "            a.holder_surname AS holderSurname, "
            + "            t.created_at     AS createdAt, "
            + "            t.deactivated_at AS deactivatedAt"
            + "       FROM transactional_accounts t "
            + "      INNER JOIN accounts a "
            + "         ON a.id = t.account_id "
            + "      WHERE (:id IS NULL OR t.id = :id) "
            + "        AND (( :keyType IS NULL OR :keyType = '') OR t.key_type = :keyType) "
            + "        AND ( (:branch IS NULL OR a.branch = :branch) "
            + "             AND (:number IS NULL OR a.number = :number) ) "
            + "        AND ((:holderName IS NULL OR :holderName = '') OR a.holder_name LIKE CONCAT('%', :holderName, '%') ) "
            , nativeQuery = true)
    List<TransactionAccountView> find(UUID id,
                                      String keyType,
                                      Integer branch,
                                      Integer number,
                                      String holderName);


}
