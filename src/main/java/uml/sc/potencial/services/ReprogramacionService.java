package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Reprogramacion;
import uml.sc.potencial.repositories.ReprogramacionRepository;

import java.util.List;

@Service
public class ReprogramacionService implements DAOService<Reprogramacion>{

    private ReprogramacionRepository reprogramacionRepository;

    public ReprogramacionService(ReprogramacionRepository reprogramacionRepository) {
        this.reprogramacionRepository = reprogramacionRepository;
    }

    @Override
    public Reprogramacion registrar(Reprogramacion p) throws Exception {
        return this.reprogramacionRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.reprogramacionRepository.deleteById(id);
    }

    @Override
    public Reprogramacion obtener(Long id) throws Exception {
        return this.reprogramacionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Reprogramacion> listar() throws Exception {
        return this.reprogramacionRepository.findAll();
    }
}
