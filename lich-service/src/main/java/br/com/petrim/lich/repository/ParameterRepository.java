package br.com.petrim.lich.repository;

import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParameterRepository extends JpaRepository<Parameter, Long>, ParameterRepositoryCustom {

    Long countByNameAndStatus(String name, StatusEnum status);

    @Query("Select p.status From Parameter p where p.id = :id ")
    StatusEnum findStatusById(@Param("id") Long id);

}
