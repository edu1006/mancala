package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.StepProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface StepProtocolRepository extends JpaRepository<StepProtocol, Long> {

    @Modifying
    @Transactional
    @Query("Update StepProtocol sp set sp.idStepProcess = :idStepProcess, sp.status = :status, sp.dateEnd = :dateEnd, sp.errorMessage = :error " +
            "where sp.id = :id")
    void updateStatus(
            @Param("id") Long id,
            @Param("idStepProcess") Long idStepProcess,
            @Param("status") String status,
            @Param("dateEnd") Date dateEnd,
            @Param("error") String errorMessage);

}
