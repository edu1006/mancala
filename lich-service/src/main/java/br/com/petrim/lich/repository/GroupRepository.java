package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {

    List<Group> findByStatus(Integer status);

    Boolean existsByName(String name);

}
