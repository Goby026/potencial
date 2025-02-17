package uml.sc.potencial.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uml.sc.potencial.entities.Cita;
import uml.sc.potencial.entities.Trabajador;
import uml.sc.potencial.services.CitaService;
import uml.sc.potencial.services.TrabajadorService;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CitaController {

    private final CitaService citaService;
    private final TrabajadorService trabajadorService;

    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    public CitaController(CitaService citaService, TrabajadorService trabajadorService) {
        this.citaService = citaService;
        this.trabajadorService = trabajadorService;
    }

    @GetMapping("/citas")
    public ResponseEntity<HashMap<String, List<Cita>>> list() throws Exception {
        List<Cita> citas = this.citaService.listar();
        HashMap<String, List<Cita>> resp = new HashMap<>();
        resp.put("citas", citas);
        return new ResponseEntity<HashMap<String,List<Cita>>>(resp, HttpStatus.OK);
    }

    /*CARGAR FORMULARIO*/
    @RequestMapping(value = "/citas/formulario", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        List<Trabajador>  psicologos = this.trabajadorService.listarPsicologos();
        Cita cita = new Cita();
        model.addAttribute("title", "registro de cita".toUpperCase());
        model.addAttribute("trabajadores", psicologos);
        model.addAttribute("cita", cita);
        return "pages/citas/formulario";
    }

    /*CARGAR FORMULARIO PARA EDITAR*/
    @RequestMapping(value = "/citas/formulario/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        List<Trabajador>  psicologos = this.trabajadorService.listarPsicologos();
        Cita cita = null;
        String title = "registrar nueva cita de trabajador";
        if (id > 0) {
            cita = this.citaService.obtener(id);
            title = "modificar cita";
            if (cita == null) {
                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
            } else {
                flash.addFlashAttribute("success", "Registro modificado correctamente.");
            }
        } else {
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/citas";
        }

        model.put("title", title.toUpperCase());
        model.put("cita", cita);
        model.put("trabajadores", psicologos);

        return "pages/citas/formulario";
    }

    /*PERSISTIR EN BASE DE DATOS (REGISTRAR/MODIFICAR)*/
    @RequestMapping(value = "/citas/save", method = RequestMethod.POST)
    public String guardar(Cita c, BindingResult result, RedirectAttributes flash) throws Exception {
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                logger.info("Error: {}", error.getDefaultMessage());
            }
        }
        String mensaje = (c.getId() !=null ) ? "Registro modificado correctamente." : "Registro creado exitosamente.";
        LocalTime time = LocalTime.now();

        c.setFechaAtencion(null);
        c.setHora_inicio(time);
        c.setHora_fin(null);
        c.setElapsedTime(null);
        c.setFechaPeritaje(null);


        this.logger.info("Cita----------------------------------> " + c.toString());

        this.citaService.registrar(c);
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/citas";
    }

    /* ELIMINAR */
    @RequestMapping(value = "/citas/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        this.citaService.eliminar(id);
        return "redirect:/citas";
    }

    /*METODO PARA MANEJAR FECHAS*/
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
