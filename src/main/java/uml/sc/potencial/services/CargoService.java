package uml.sc.potencial.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uml.sc.potencial.entities.Cargo;
import uml.sc.potencial.repositories.CargoTrabajadorRepository;

import java.util.List;

@Service
public class CargoService implements DAOService<Cargo> {

    private final CargoTrabajadorRepository repository;

    public CargoService(CargoTrabajadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cargo registrar(Cargo p) throws Exception {
        return this.repository.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repository.deleteById(id);
    }

    @Override
    public Cargo obtener(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public List<Cargo> listar() throws Exception {
        return this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    //    listar con paginacion
    public void listarPaginacion() throws Exception {
        Page<Cargo> page = this.repository.findAll(PageRequest.of(0,5));
        System.out.println("total de registros: " + page.getTotalElements());
        System.out.println("total de paginas: " + page.getTotalPages());
        for (Cargo c : page.getContent()){
            System.out.println(c.toString());
        }
    }

    //    listar con paginacion y ordenamiento
    public void listarPaginacionOrdenamiento() throws Exception {
        Page<Cargo> page = this.repository.findAll(PageRequest.of(0,5, Sort.by("descripcion")));
        System.out.println("total de registros: " + page.getTotalElements());
        System.out.println("total de paginas: " + page.getTotalPages());
        for (Cargo c : page.getContent()){
            System.out.println(c.toString());
        }
    }
}
