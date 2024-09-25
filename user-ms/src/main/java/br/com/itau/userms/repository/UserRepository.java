package br.com.itau.userms.repository;

import br.com.itau.userms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
