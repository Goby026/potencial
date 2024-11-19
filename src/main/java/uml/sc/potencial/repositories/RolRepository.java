package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uml.sc.potencial.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
