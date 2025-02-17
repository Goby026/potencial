package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uml.sc.potencial.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
