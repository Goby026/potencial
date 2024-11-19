package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.EstadoCita;

@Repository
public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Long> {
}
