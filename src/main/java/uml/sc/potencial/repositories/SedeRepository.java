package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Sede;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {
}
