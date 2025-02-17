package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Usuario;
import uml.sc.potencial.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService implements DAOService<Usuario> {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario registrar(Usuario p) throws Exception {
        return this.usuarioRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario obtener(Long id) throws Exception {
        return this.usuarioRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Usuario> listar() throws Exception {
        return this.usuarioRepository.findAll();
    }
}
