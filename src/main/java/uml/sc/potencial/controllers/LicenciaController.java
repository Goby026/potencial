package uml.sc.potencial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uml.sc.potencial.entities.Permiso;
import uml.sc.potencial.services.LicenciaService;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class LicenciaController {
    
    private final LicenciaService licenciaService;

    public LicenciaController(LicenciaService licenciaService) {
        this.licenciaService = licenciaService;
    }

    @GetMapping("/licencias")
    public ResponseEntity<HashMap<String, List<Permiso>>> list() throws Exception {
        List<Permiso> permisos = this.licenciaService.listar();
        HashMap<String, List<Permiso>> resp = new HashMap<>();
        resp.put("licencias", permisos);
        return new ResponseEntity<HashMap<String, List<Permiso>>>(resp, HttpStatus.OK);
    }

    @GetMapping("/licencias/{id}")
    public ResponseEntity<Permiso> get(@PathVariable(value = "id") Long id) throws Exception {
        Permiso permiso = licenciaService.obtener(id);
        return new ResponseEntity<Permiso>(permiso, HttpStatus.OK);
    }

    @PostMapping("/licencias")
    public ResponseEntity<Permiso> add(@RequestBody Permiso p) throws Exception {
        try {
            Permiso cita = licenciaService.registrar(p);
            return new ResponseEntity<Permiso>(cita, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Permiso>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/licencias/{id}")
    public ResponseEntity<Permiso> update(@RequestBody Permiso p, @PathVariable Long id) throws Exception {
        try {
            Permiso permiso = licenciaService.registrar(p);
            return new ResponseEntity<Permiso>(permiso, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Permiso>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/licencias/{id}")
    public ResponseEntity<Permiso> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            licenciaService.eliminar(id);
            return new ResponseEntity<Permiso>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Permiso>(HttpStatus.NOT_FOUND);
        }
    }
}
