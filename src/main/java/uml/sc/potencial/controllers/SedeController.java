package uml.sc.potencial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uml.sc.potencial.entities.Sede;
import uml.sc.potencial.services.SedeService;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class SedeController {

    public final String title = "SEDES";
    
    private final SedeService service;

    public SedeController(SedeService service) {
        this.service = service;
    }
    
    @GetMapping("/sedes")
    public String list(Model model) throws Exception {
        List<Sede> sedes = this.service.listar();
        model.addAttribute("sedes", sedes);
        model.addAttribute("title", this.title);
        return "pages/sedes/sedes";
    }

    @RequestMapping("/sedes/create")
    public String formulario(Model model) throws Exception {
        Sede sede = new Sede();
        model.addAttribute("title", "Nueva Sede");
        model.addAttribute("sede", sede);
        return "pages/sedes/create";
    }

    @RequestMapping("/sedes/create/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Sede sede = null;
        if (id>0){
            sede = this.service.obtener(id);
            if (sede == null){
                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
            }else{
                flash.addFlashAttribute("success", "Registro modificado correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/sedes";
        }

        model.put("sede", sede);
        model.put("title", this.title);

        return "pages/sedes/create";
    }

    @PostMapping("/sedes")
    public ResponseEntity<Sede> add(@RequestBody Sede p) throws Exception {
        try {
            Sede sede = service.registrar(p);
            return new ResponseEntity<Sede>(sede, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Sede>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/sedes/{id}")
    public ResponseEntity<Sede> update(@RequestBody Sede p, @PathVariable Long id) throws Exception {
        try {
            Sede sede = service.registrar(p);
            return new ResponseEntity<Sede>(sede, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Sede>(HttpStatus.NOT_FOUND);
        }
    }
}
