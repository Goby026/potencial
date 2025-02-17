package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Tipotrabajador;

@Repository
public interface TipoTrabajadorRepository extends JpaRepository<Tipotrabajador,Long> {
}
