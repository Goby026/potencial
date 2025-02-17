package uml.sc.potencial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uml.sc.potencial.entities.Equipo;
import uml.sc.potencial.services.EquipoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class EquipoController {

    private final EquipoService service;

    public EquipoController(EquipoService service) {
        this.service = service;
    }

    @GetMapping("/equipos")
    public ResponseEntity<HashMap<String, List<Equipo>>> listar() throws Exception{

        List<Equipo> equipos = this.service.listar();

        HashMap<String, List<Equipo>> resp = new HashMap<>();
        resp.put("equipos", equipos);

        return new ResponseEntity<HashMap<String, List<Equipo>>>(resp, HttpStatus.OK);
    }


    @GetMapping("/equipos/{id}")
    public ResponseEntity<Equipo> obtener(@PathVariable(value = "id") Long id) throws Exception{
        try {

            Equipo equipo = this.service.obtener(id);

            return new ResponseEntity<Equipo>(equipo, HttpStatus.OK);

        }catch (NoSuchElementException ex){
            System.out.println("NO ENCONTRADO " + ex.getMessage());
            return new ResponseEntity<Equipo>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/equipos")
    public ResponseEntity<Equipo> registrar(@RequestBody Equipo e) throws Exception{
        try{
            Equipo equipo = this.service.registrar(e);
            return new ResponseEntity<Equipo>(equipo, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Equipo>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/equipos/{id}")
    public ResponseEntity<Equipo> actualizar(@RequestBody Equipo e,@PathVariable(value = "id") Long id) throws Exception{
        try{
            Equipo equipo = this.service.registrar(e);
            return new ResponseEntity<Equipo>(equipo, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Equipo>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Equipo> borrar(@PathVariable("id") Long id) throws Exception{
        try {
            this.service.eliminar(id);
            return new ResponseEntity<Equipo>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Equipo>(HttpStatus.NOT_FOUND);
        }
    }



}
