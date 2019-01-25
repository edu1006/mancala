package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

}
