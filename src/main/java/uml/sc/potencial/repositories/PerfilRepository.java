package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
