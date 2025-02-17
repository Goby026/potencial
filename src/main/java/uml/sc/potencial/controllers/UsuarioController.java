package uml.sc.potencial.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uml.sc.potencial.entities.Usuario;
import uml.sc.potencial.services.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        List<Usuario> usuarios = this.service.listar();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("title", "usuarios".toUpperCase());
        return "pages/usuarios/usuarios";
    }

    @RequestMapping(value = "/usuarios/create", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        Usuario usuario = new Usuario();
        model.addAttribute("title", "registrar usuario".toUpperCase());
        model.addAttribute("usuario", usuario);
        return "pages/usuarios/create";
    }

    @RequestMapping(value = "/usuarios/create/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Usuario usuario = null;
        String title = "registrar usuario";
        if (id > 0) {
            usuario = service.obtener(id);
            title = "modificar usuario";
            if (usuario == null) {
                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
            } else {
                flash.addFlashAttribute("success", "Registro modificado correctamente.");
            }
        } else {
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/usuarios";
        }

        model.put("usuario", usuario);
        model.put("title", title.toUpperCase());

        return "pages/usuarios/create";
    }

    @RequestMapping(value = "/usuarios/save", method = RequestMethod.POST)
    public String guardar(Usuario usuario, BindingResult result, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso usuario
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                logger.info("Error: {}", error.getDefaultMessage());
            }
            return "redirect:/usuarios/create";
        }

        String mensaje = (usuario.getId() != null) ? "Registro modificado correctamente." : "Registro creado exitosamente.";

        service.registrar(usuario);
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/usuarios";
    }

    @DeleteMapping("/usuarios/{id}")
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        this.service.eliminar(id);
        return "redirect:/usuarios";
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
