package uml.sc.potencial.controllers;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uml.sc.potencial.entities.Cargo;
import uml.sc.potencial.services.CargoService;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1")
public class CargoController {

    private final CargoService service;
    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    public CargoController(CargoService service) {
        this.service = service;
    }

    @GetMapping("/cargos")
    public ResponseEntity<HashMap<String, List<Cargo>>> listar() throws Exception {
        List<Cargo> cargos = this.service.listar();

        HashMap<String, List<Cargo>> resp = new HashMap<>();

        resp.put("cargos", cargos);

        return new ResponseEntity<HashMap<String, List<Cargo>>>(resp, HttpStatus.OK);
    }

    @GetMapping("/cargos/{id}")
    public ResponseEntity<Cargo> obtener(@PathVariable(value = "id") Long id) throws Exception{
        try {

            Cargo cargo = this.service.obtener(id);

            return new ResponseEntity<Cargo>(cargo, HttpStatus.OK);

        }catch (NoSuchElementException ex){
            return new ResponseEntity<Cargo>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cargos")
    public ResponseEntity<Cargo> registrar(@RequestBody Cargo c) throws Exception {
        try {
            Cargo cargo = this.service.registrar(c);
            return new ResponseEntity<Cargo>(cargo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Cargo>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cargos/{id}")
    public ResponseEntity<Cargo> actualizar(@RequestBody Cargo c, @PathVariable(value = "id") Long id) throws Exception {
        try {
            Cargo cargo = service.registrar(c);
            return new ResponseEntity<Cargo>(cargo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Cargo>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cargos/{id}")
    public ResponseEntity<Cargo> borrar(@PathVariable(value = "id") Long id) throws Exception {
        try {
            this.service.eliminar(id);
            return new ResponseEntity<Cargo>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Cargo>(HttpStatus.BAD_REQUEST);
        }
    }

    //    metodo para establecer valores que se pueden reutilizar en la vista a nivel GLOBAL
//    @ModelAttribute("estadoCitas")
//    public List<EstadoCita> listaEstados() {
//        return Arrays.asList(
//                new EstadoCita(1L, "Pendiente", "primary", 1, null, null),
//                new EstadoCita(2L, "Reprogramado", "secondary", 1, null, null),
//                new EstadoCita(2L, "No asistió", "warning", 1, null, null),
//                new EstadoCita(2L, "Cancelado", "danger", 1, null, null)
//        );
//    }

}
