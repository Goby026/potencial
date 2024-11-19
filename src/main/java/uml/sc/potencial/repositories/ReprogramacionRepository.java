package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Reprogramacion;

@Repository
public interface ReprogramacionRepository extends JpaRepository<Reprogramacion, Long> {
}
