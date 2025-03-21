package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Permiso;

@Repository
public interface LicenciaRepository extends JpaRepository<Permiso, Long> {
}
