package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uml.sc.potencial.entities.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
