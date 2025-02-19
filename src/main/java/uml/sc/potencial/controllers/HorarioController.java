package uml.sc.potencial.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uml.sc.potencial.entities.Horario;
import uml.sc.potencial.services.HorarioService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class HorarioController {

    private final HorarioService service;
    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    public HorarioController(HorarioService service) {
        this.service = service;
    }

    /*PAGINA INICIAL*/
    @RequestMapping(value = "/horarios")
    public String index(Model model) throws Exception{
        List<Horario> horarios = this.service.listar();
        String title = "HORARIOS";
        model.addAttribute("title", title);
        model.addAttribute("horarios", horarios);
        return "pages/horarios/index";
    }

    /*CARGAR FORMULARIO*/
    @RequestMapping(value = "/horarios/formulario", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        Horario horario = new Horario();
        model.addAttribute("horario", horario);
        model.addAttribute("title", "registrar horario".toUpperCase());
        return "pages/horarios/formulario";
    }

    /*CARGAR FORMULARIO PARA EDITAR*/
    @RequestMapping(value = "/horarios/formulario/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
//        List<Tipotrabajador> tipos = this.service.listar();
        Horario horario = null;
        String title = "registrar nuevo horario";
        if (id > 0) {
            horario = this.service.obtener(id);
            title = "modificar horario";
            if (horario == null) {
                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
            } else {
                flash.addFlashAttribute("success", "Registro modificado correctamente.");
            }
        } else {
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/horarios";
        }

        model.put("title", title.toUpperCase());
        model.put("horario", horario);

        return "pages/horarios/formulario";
    }

    /*PERSISTIR EN BASE DE DATOS (REGISTRAR/MODIFICAR)*/
    @RequestMapping(value = "/horarios/save", method = RequestMethod.POST)
    public String guardar(Horario t, BindingResult result, RedirectAttributes flash) throws Exception {
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                logger.info("Error: {}", error.getDefaultMessage());
            }
        }
        String mensaje = (t.getId() !=null ) ? "Registro modificado correctamente." : "Registro creado exitosamente.";
        this.service.registrar(t);
        flash.addFlashAttribute("success", mensaje);

        return "redirect:/horarios";
    }

    /* ELIMINAR */
    @RequestMapping(value = "/horarios/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        this.service.eliminar(id);
        return "redirect:/horarios";
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

}
