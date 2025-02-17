package uml.sc.potencial.services;

import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Permiso;
import uml.sc.potencial.repositories.LicenciaRepository;

import java.util.List;

@Service
public class LicenciaService implements DAOService<Permiso>{

    private final LicenciaRepository licenciaRepository;

    public LicenciaService(LicenciaRepository licenciaRepository) {
        this.licenciaRepository = licenciaRepository;
    }

    @Override
    public Permiso registrar(Permiso p) throws Exception {
        return this.licenciaRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.licenciaRepository.deleteById(id);
    }

    @Override
    public Permiso obtener(Long id) throws Exception {
        return this.licenciaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Permiso> listar() throws Exception {
        return this.licenciaRepository.findAll();
    }
}
