package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
