package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long>, AgentRepositoryCustom {

    @Query("Select a.id From Agent a " +
            "Where a.name = :name " +
            "or (a.address = :address and a.port = :port) ")
    List<Long> getIdByNameOrAddress(@Param("name") String name,
                              @Param("address") String address,
                              @Param("port") Long port);

}
