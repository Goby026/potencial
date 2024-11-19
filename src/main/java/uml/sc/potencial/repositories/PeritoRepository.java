package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uml.sc.potencial.entities.Perito;

public interface PeritoRepository extends JpaRepository<Perito, Long> {
}
