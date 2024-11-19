package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
}
