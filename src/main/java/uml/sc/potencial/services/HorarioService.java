package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Horario;
import uml.sc.potencial.repositories.HorarioRepository;

import java.util.List;

@Service
public class HorarioService implements DAOService<Horario> {

    private HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    @Override
    public Horario registrar(Horario p) throws Exception {
        return this.horarioRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.horarioRepository.deleteById(id);
    }

    @Override
    public Horario obtener(Long id) throws Exception {
        return this.horarioRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Horario> listar() throws Exception {
        return this.horarioRepository.findAll();
    }
}
