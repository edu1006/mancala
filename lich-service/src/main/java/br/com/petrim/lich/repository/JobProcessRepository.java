package br.com.petrim.lich.repository;

import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.enums.YesNoEnum;
import br.com.petrim.lich.model.JobProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobProcessRepository extends JpaRepository<JobProcess, Long>, JobProcessRepositoryCustom {

    @Query("From JobProcess j " +
            "join fetch j.stepsProcesses sp " +
            "left join fetch sp.stepsParallels spl " +
            "where j.id = :id ")
    Optional<JobProcess> loadById(@Param("id") Long id);

    @Modifying
    @Query("update JobProcess j set j.status = :status where j.id = :id")
    void updateStatus(@Param("id") Long id,
                      @Param("status") StatusEnum status);

    @Query("Select new br.com.petrim.lich.model.JobProcess(jp.id, jp.idProcess) " +
            "From JobProcess jp " +
            "where jp.innerJob = :innerJob " +
            "and jp.status = :status ")
    List<JobProcess> findInnerJobsEnable(
            @Param("innerJob") YesNoEnum innerJob,
            @Param("status") StatusEnum status);

}
