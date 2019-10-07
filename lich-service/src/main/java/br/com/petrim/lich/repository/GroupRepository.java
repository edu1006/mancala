package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {

    @Modifying
    @Transactional
    @Query(value = "insert into access_group (id, name, status, version) values (1, 'ADMIN', 1, 0)",
            nativeQuery = true)
    void insertAdminGroup();
    
    @Query(value = "select f.id from Group g "
    		+ "inner join g.functionalities f "
    		+ "where g.id = 1 ")
    List<Long> findAdminGroupFunctionalities();
    
    @Modifying
    @Transactional
    @Query(value = "insert into access_group_functionality (id_access_group, id_access_functionality) values (1, :idFunctionality)",
    		nativeQuery = true)
    void insertAdminGroupFuncionality(@Param("idFunctionality") Long idFunctionality);

    List<Group> findByStatus(Integer status);

    Boolean existsByName(String name);

}
