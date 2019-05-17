package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {

    @Modifying
    @Transactional
    @Query(value = "insert into access_group (id, name, status, version) values (1, 'ADMIN', 1, 0)",
            nativeQuery = true)
    void insertAdminGroup();

    List<Group> findByStatus(Integer status);

    Boolean existsByName(String name);

}
