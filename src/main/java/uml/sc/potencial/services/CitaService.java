package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Cita;
import uml.sc.potencial.repositories.CitaRepository;

import java.util.List;

@Service
public class CitaService implements DAOService<Cita>{

    private CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public Cita registrar(Cita p) throws Exception {
        return this.citaRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.citaRepository.deleteById(id);
    }

    @Override
    public Cita obtener(Long id) throws Exception {
        return this.citaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Cita> listar() throws Exception {
        return this.citaRepository.findAll();
    }
}
