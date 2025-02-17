package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Equipo;
import uml.sc.potencial.repositories.EquipoRepository;

import java.util.List;

@Service
public class EquipoService implements DAOService<Equipo> {

    private final EquipoRepository repository;

    public EquipoService(EquipoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Equipo registrar(Equipo p) throws Exception {
        return this.repository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repository.deleteById(id);
    }

    @Override
    public Equipo obtener(Long id) throws Exception {
        return this.repository.findById(id).get();
    }

    @Override
    public List<Equipo> listar() throws Exception {
        return this.repository.findAll();
    }
}
