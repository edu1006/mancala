package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
