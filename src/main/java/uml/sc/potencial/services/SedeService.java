package uml.sc.potencial.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Sede;
import uml.sc.potencial.repositories.SedeRepository;

import java.util.List;

@Service
public class SedeService implements DAOService<Sede>{

    private final SedeRepository sedeRepository;

    public SedeService(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @Override
    public Sede registrar(Sede p) throws Exception {
        return this.sedeRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.sedeRepository.deleteById(id);
    }

    @Override
    public Sede obtener(Long id) throws Exception {
        return this.sedeRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Sede> listar() throws Exception {
        return this.sedeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
