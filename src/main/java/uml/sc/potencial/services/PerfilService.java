package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Perfil;
import uml.sc.potencial.repositories.PerfilRepository;

import java.util.List;

@Service
public class PerfilService implements DAOService<Perfil> {

    private final PerfilRepository repository;

    public PerfilService(PerfilRepository repository) {
        this.repository = repository;
    }

    @Override
    public Perfil registrar(Perfil p) throws Exception {
        return this.repository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repository.deleteById(id);
    }

    @Override
    public Perfil obtener(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public List<Perfil> listar() throws Exception {
        return this.repository.findAll();
    }
}
