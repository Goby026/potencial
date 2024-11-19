package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.User;
import uml.sc.potencial.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements DAOService<User> {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registrar(User p) throws Exception {
        return this.userRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.userRepository.deleteById(id);
    }

    @Override
    public User obtener(Long id) throws Exception {
        return this.userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> listar() throws Exception {
        return this.userRepository.findAll();
    }
}
