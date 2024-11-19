package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.SubSede;

@Repository
public interface SubSedeRepository extends JpaRepository<SubSede, Long> {
}
