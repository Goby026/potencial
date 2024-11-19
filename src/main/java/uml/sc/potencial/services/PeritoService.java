package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Perito;
import uml.sc.potencial.repositories.PeritoRepository;

import java.util.List;

@Service
public class PeritoService implements DAOService<Perito> {

    private PeritoRepository peritoRepository;

    public PeritoService(PeritoRepository peritoRepository) {
        this.peritoRepository = peritoRepository;
    }

    @Override
    public Perito registrar(Perito p) throws Exception {
        return this.peritoRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.peritoRepository.deleteById(id);
    }

    @Override
    public Perito obtener(Long id) throws Exception {
        return this.peritoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Perito> listar() throws Exception {
        return this.peritoRepository.findAll();
    }
}
