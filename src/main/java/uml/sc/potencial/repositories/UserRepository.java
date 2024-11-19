package uml.sc.potencial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uml.sc.potencial.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
