package uml.sc.potencial.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uml.sc.potencial.entities.Tipotrabajador;
import uml.sc.potencial.services.TipoTrabajadorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TipoTrabajadorController {

    private final TipoTrabajadorService service;
    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    public TipoTrabajadorController(TipoTrabajadorService service) {
        this.service = service;
    }

    /*PAGINA INICIAL*/
    @RequestMapping(value = "/tipo-trabajador")
    public String index(Model model) throws Exception{
        List<Tipotrabajador> tipos = this.service.listar();
        String title = "TIPO DE TRABAJADOR";
        model.addAttribute("title", title);
        model.addAttribute("tipos", tipos);
        return "pages/tipotrabajadores/index";
    }

    /*CARGAR FORMULARIO*/
    @RequestMapping(value = "/tipo-trabajador/formulario", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        Tipotrabajador tipotrabajador = new Tipotrabajador();
//        List<Sede> sedes = this.sedeService.listar();
//        List<Cargo> cargos = this.cargoService.listar();
        model.addAttribute("tipo", tipotrabajador);
        model.addAttribute("title", "registrar tipo de trabajador".toUpperCase());
        return "pages/tipotrabajadores/formulario";
    }

    /*CARGAR FORMULARIO PARA EDITAR*/
    @RequestMapping(value = "/tipo-trabajador/formulario/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
//        List<Tipotrabajador> tipos = this.service.listar();
        Tipotrabajador tipo = null;
        String title = "registrar nuevo tipo de trabajador";
        if (id > 0) {
            tipo = this.service.obtener(id);
            title = "modificar tipo de trabajador";
            if (tipo == null) {
                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
            } else {
                flash.addFlashAttribute("success", "Registro modificado correctamente.");
            }
        } else {
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/tipo-trabajador";
        }

        model.put("title", title.toUpperCase());
        model.put("tipo", tipo);
//        model.put("tipos", tipos);

        return "pages/tipotrabajadores/formulario";
    }

    /*PERSISTIR EN BASE DE DATOS (REGISTRAR/MODIFICAR)*/
    @RequestMapping(value = "/tipo-trabajador/save", method = RequestMethod.POST)
    public String guardar(Tipotrabajador t, BindingResult result, RedirectAttributes flash) throws Exception {
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                logger.info("Error: {}", error.getDefaultMessage());
            }
        }
        String mensaje = (t.getId() !=null ) ? "Registro modificado correctamente." : "Registro creado exitosamente.";
        this.service.registrar(t);
        flash.addFlashAttribute("success", mensaje);

        return "redirect:/tipo-trabajador";
    }

    /* ELIMINAR */
    @RequestMapping(value = "/tipo-trabajador/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        this.service.eliminar(id);
        return "redirect:/tipo-trabajador";
    }

}
