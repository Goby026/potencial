package uml.sc.potencial.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uml.sc.potencial.entities.Cargo;
import uml.sc.potencial.entities.EstadoCita;
import uml.sc.potencial.services.CargoService;

import java.util.*;

@Controller
public class CargoController {

    private final CargoService service;
    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    public CargoController(CargoService service) {
        this.service = service;
    }

    @RequestMapping(value = "/cargos", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        List<Cargo> cargos = this.service.listar();
        model.addAttribute("cargos", cargos);
        model.addAttribute("title", "cargos".toUpperCase());
        return "pages/cargos/cargos";
    }

    @RequestMapping(value = "/cargos/create", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        Cargo cargo = new Cargo();
        model.addAttribute("title", "registrar cargo".toUpperCase());
        model.addAttribute("cargo", cargo);
        return "pages/cargos/create";
    }

    @RequestMapping(value = "/cargos/create/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Cargo cargo = null;
        String title = "registrar cargo";
        if (id > 0) {
            cargo = service.obtener(id);
            title = "modificar cargo";
            if (cargo == null) {
                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
            } else {
                flash.addFlashAttribute("success", "Registro modificado correctamente.");
            }
        } else {
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/cargos";
        }

        model.put("cargo", cargo);
        model.put("title", title.toUpperCase());

        return "pages/cargos/create";
    }

    @RequestMapping(value = "/cargos/save", method = RequestMethod.POST)
    public String guardar(Cargo cargo, BindingResult result, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                logger.info("Error: {}", error.getDefaultMessage());
            }
            return "redirect:/cargos/create";
        }

        String mensaje = (cargo.getId() != null) ? "Registro modificado correctamente." : "Registro creado exitosamente.";

        service.registrar(cargo);
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/cargos";
    }

    @DeleteMapping("/cargos/{id}")
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        this.service.eliminar(id);
        return "redirect:/cargos";
    }

    //    metodo para establecer valores que se pueden reutilizar en la vista a nivel GLOBAL
    @ModelAttribute("estadoCitas")
    public List<EstadoCita> listaEstados() {
        return Arrays.asList(
                new EstadoCita(1L, "Pendiente", "primary", 1, null, null),
                new EstadoCita(2L, "Reprogramado", "secondary", 1, null, null),
                new EstadoCita(2L, "No asistió", "warning", 1, null, null),
                new EstadoCita(2L, "Cancelado", "danger", 1, null, null)
        );
    }

}
