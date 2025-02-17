package uml.sc.potencial.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.SubSede;
import uml.sc.potencial.repositories.SubSedeRepository;

import java.util.List;

@Service
public class SubSedeService implements DAOService<SubSede>{

    private SubSedeRepository repository;

    public SubSedeService(SubSedeRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubSede registrar(SubSede p) throws Exception {
        return this.repository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repository.deleteById(id);
    }

    @Override
    public SubSede obtener(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public List<SubSede> listar() throws Exception {
        return this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
