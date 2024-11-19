package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.EstadoCita;
import uml.sc.potencial.repositories.EstadoCitaRepository;

import java.util.List;

@Service
public class EstadoCitaService implements DAOService<EstadoCita> {

    private EstadoCitaRepository estadoCitaRepository;

    public EstadoCitaService(EstadoCitaRepository estadoCitaRepository) {
        this.estadoCitaRepository = estadoCitaRepository;
    }

    @Override
    public EstadoCita registrar(EstadoCita p) throws Exception {
        return this.estadoCitaRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.estadoCitaRepository.deleteById(id);
    }

    @Override
    public EstadoCita obtener(Long id) throws Exception {
        return this.estadoCitaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<EstadoCita> listar() throws Exception {
        return this.estadoCitaRepository.findAll();
    }
}
