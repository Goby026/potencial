package uml.sc.potencial.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uml.sc.potencial.entities.Perfil;
import uml.sc.potencial.services.PerfilService;

import java.util.List;
import java.util.Map;

@Controller
public class PerfilController {
    private static final Logger logger = LoggerFactory.getLogger(PerfilController.class);
    private final PerfilService service;

    public PerfilController(PerfilService service) {
        this.service = service;
    }

    @RequestMapping(value = "/perfiles", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        List<Perfil> perfiles = this.service.listar();
        model.addAttribute("perfiles", perfiles);
        model.addAttribute("title", "perfiles".toUpperCase());
        return "pages/perfiles/index";
    }

    @RequestMapping(value = "/perfiles/create", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        Perfil perfil = new Perfil();
        model.addAttribute("title", "registrar perfil".toUpperCase());
        model.addAttribute("perfil", perfil);
        return "pages/perfiles/formulario";
    }

    @RequestMapping(value = "/perfiles/create/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Perfil perfil = null;
        String title = "registrar perfil";
        if (id > 0) {
            perfil = service.obtener(id);
            title = "modificar perfil";
            if (perfil == null) {
                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
            } else {
                flash.addFlashAttribute("success", "Registro modificado correctamente.");
            }
        } else {
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/perfiles";
        }

        model.put("perfil", perfil);
        model.put("title", title.toUpperCase());

        return "pages/perfiles/formulario";
    }

    @RequestMapping(value = "/perfiles/save", method = RequestMethod.POST)
    public String guardar(Perfil perfil, BindingResult result, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso perfil
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                logger.info("Error: {}", error.getDefaultMessage());
            }
            return "redirect:/perfiles/formulario";
        }

        String mensaje = (perfil.getId() != null) ? "Registro modificado correctamente." : "Registro creado exitosamente.";

        service.registrar(perfil);
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/perfiles";
    }

    @DeleteMapping("/perfiles/{id}")
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        this.service.eliminar(id);
        return "redirect:/perfiles";
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
