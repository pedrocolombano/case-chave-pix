package br.com.itau.accountms.repository;

import br.com.itau.accountms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
