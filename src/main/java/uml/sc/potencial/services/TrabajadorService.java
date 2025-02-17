package uml.sc.potencial.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Cargo;
import uml.sc.potencial.entities.Trabajador;
import uml.sc.potencial.repositories.TrabajadorRepository;

import java.util.List;

@Service
public class TrabajadorService implements DAOService<Trabajador> {

    private final TrabajadorRepository trabajadorRepository;

    public TrabajadorService(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    @Override
    public Trabajador registrar(Trabajador p) throws Exception {
        return this.trabajadorRepository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.trabajadorRepository.deleteById(id);
    }

    @Override
    public Trabajador obtener(Long id) throws Exception {
        return this.trabajadorRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Trabajador> listar() throws Exception {
        return trabajadorRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Trabajador> listarPsicologos() throws Exception {
        Cargo c = new Cargo();
        c.setId(5L);
        List<Trabajador> trabajadores = trabajadorRepository.findByCargo(c);
        System.out.println("CARGO----> : " + c.toString());
        System.out.println(trabajadores.toString());
        return trabajadores;
    }
}
