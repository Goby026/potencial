package uml.sc.potencial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uml.sc.potencial.entities.Perito;
import uml.sc.potencial.services.PeritoService;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class PeritoController {

    private final PeritoService peritoService;

    public PeritoController(PeritoService peritoService) {
        this.peritoService = peritoService;
    }

    @GetMapping("/peritos")
    public ResponseEntity<HashMap<String, List<Perito>>> list() throws Exception {
        List<Perito> peritos = this.peritoService.listar();
        HashMap<String, List<Perito>> resp = new HashMap<>();
        resp.put("peritos", peritos);
        return new ResponseEntity<HashMap<String, List<Perito>>>(resp, HttpStatus.OK);
    }

    @PostMapping("/peritos")
    public ResponseEntity<Perito> add(@RequestBody Perito p) throws Exception {
        try {
            Perito perito = peritoService.registrar(p);
            return new ResponseEntity<Perito>(perito, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Perito>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/peritos/{id}")
    public ResponseEntity<Perito> update(@RequestBody Perito p, @PathVariable Long id) throws Exception {
        try {
            Perito perito = peritoService.registrar(p);
            return new ResponseEntity<Perito>(perito, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Perito>(HttpStatus.NOT_FOUND);
        }
    }

}
