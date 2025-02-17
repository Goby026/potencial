package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Tipotrabajador;
import uml.sc.potencial.repositories.TipoTrabajadorRepository;

import java.util.List;

@Service
public class TipoTrabajadorService implements DAOService<Tipotrabajador> {

    private final TipoTrabajadorRepository repository;

    public TipoTrabajadorService(TipoTrabajadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tipotrabajador registrar(Tipotrabajador p) throws Exception {
        return this.repository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repository.deleteById(id);
    }

    @Override
    public Tipotrabajador obtener(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public List<Tipotrabajador> listar() throws Exception {
        return this.repository.findAll();
    }
}
