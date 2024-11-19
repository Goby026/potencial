package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Licencia;
import uml.sc.potencial.repositories.LicenciaRepository;

import java.util.List;

@Service
public class LicenciaService implements DAOService<Licencia>{

    private final LicenciaRepository licenciaRepository;

    public LicenciaService(LicenciaRepository licenciaRepository) {
        this.licenciaRepository = licenciaRepository;
    }

    @Override
    public Licencia registrar(Licencia p) throws Exception {
        return this.licenciaRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.licenciaRepository.deleteById(id);
    }

    @Override
    public Licencia obtener(Long id) throws Exception {
        return this.licenciaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Licencia> listar() throws Exception {
        return this.licenciaRepository.findAll();
    }
}
