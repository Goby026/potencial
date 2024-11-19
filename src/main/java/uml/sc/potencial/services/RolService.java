package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Rol;
import uml.sc.potencial.repositories.RolRepository;

import java.util.List;

@Service
public class RolService implements DAOService<Rol> {

    private RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol registrar(Rol p) throws Exception {
        return this.rolRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.rolRepository.deleteById(id);
    }

    @Override
    public Rol obtener(Long id) throws Exception {
        return this.rolRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Rol> listar() throws Exception {
        return this.rolRepository.findAll();
    }
}
