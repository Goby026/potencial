package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
