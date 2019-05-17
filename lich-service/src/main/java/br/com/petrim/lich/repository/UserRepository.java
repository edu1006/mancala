package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    @Modifying
    @Transactional
    @Query(value = "insert into user (id, date_insert, login, name, password, status, version) " +
            "values (1, :dateInsert, 'admin', 'Administrator', :password, 1, 0)",
            nativeQuery = true)
    void insertAdminUser(
            @Param("dateInsert") Date dateInsert,
            @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "insert into user_access_group (id_user, id_access_group) values (1,1)",
            nativeQuery = true)
    void insertAdminUserGroup();

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    @Query("From User u " +
            "left join fetch u.groups g " +
            "where u.id = :id ")
    Optional<User> loadById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("Update User u set u.password = :password where u.id = :id")
    void updatePassword(
            @Param("id") Long id,
            @Param("password") String password);

    @Query("Select u.id From User u " +
            "where u.login = :login ")
    Long findIdByLogin(@Param("login") String login);

}
