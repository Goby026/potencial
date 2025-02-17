package uml.sc.potencial.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uml.sc.potencial.entities.Cargo;
import uml.sc.potencial.entities.Sede;
import uml.sc.potencial.entities.Tipotrabajador;
import uml.sc.potencial.entities.Trabajador;
import uml.sc.potencial.helpers.Utileria;
import uml.sc.potencial.services.CargoService;
import uml.sc.potencial.services.SedeService;
import uml.sc.potencial.services.TipoTrabajadorService;
import uml.sc.potencial.services.TrabajadorService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TrabajadorController {

    private final TrabajadorService trabajadorService;
    private final SedeService sedeService;
    private final CargoService cargoService;
    private final TipoTrabajadorService tipoTrabajadorService;

    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    @Value("${mi.config.ruta}")
    private String PATH_IMG;

    public TrabajadorController(TrabajadorService trabajadorService, SedeService sedeService, CargoService cargoService, TipoTrabajadorService tipoTrabajadorService) {
        this.trabajadorService = trabajadorService;
        this.sedeService = sedeService;
        this.cargoService = cargoService;
        this.tipoTrabajadorService = tipoTrabajadorService;
    }

    @RequestMapping(value = "/trabajadores", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        List<Trabajador> trabajadores = this.trabajadorService.listar();
        model.addAttribute("trabajadores", trabajadores);
        model.addAttribute("title", "trabajadores".toUpperCase());
        return "pages/trabajadores/trabajadores";
    }

    /*CARGAR FORMULARIO*/
    @RequestMapping(value = "/trabajadores/create", method = RequestMethod.GET)
    public String create(Model model) throws Exception {
        Trabajador trabajador = new Trabajador();
        List<Sede> sedes = this.sedeService.listar();
        List<Cargo> cargos = this.cargoService.listar();
        List<Tipotrabajador> tipos = this.tipoTrabajadorService.listar();
        //cargar sedes
        //cargar cargos
        model.addAttribute("title", "registrar trabajador".toUpperCase());
        model.addAttribute("trabajador", trabajador);
        model.addAttribute("sedes", sedes);
        model.addAttribute("cargos", cargos);
        model.addAttribute("tipos", tipos);
        return "pages/trabajadores/create";
    }

    /* CARGAR FORMULARIO PARA REGISTRAR/EDITAR */
    @RequestMapping(value = "/trabajadores/create/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        List<Sede> sedes = this.sedeService.listar();
        List<Cargo> cargos = this.cargoService.listar();
        List<Tipotrabajador> tipos = this.tipoTrabajadorService.listar();
        Trabajador trabajador = null;
        String title = "registrar nuevo trabajador";
        if (id > 0) {
            trabajador = this.trabajadorService.obtener(id);
            title = "modificar trabajador";
            if (trabajador == null) {
                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
            } else {
                flash.addFlashAttribute("success", "Registro modificado correctamente.");
            }
        } else {
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/trabajadores";
        }

        model.put("title", title.toUpperCase());
        model.put("trabajador", trabajador);
        model.put("sedes", sedes);
        model.put("cargos", cargos);
        model.put("tipos", tipos);

        return "pages/trabajadores/create";
    }

    /*PERSISTIR EN BASE DE DATOS (REGISTRAR/MODIFICAR)*/
    @RequestMapping(value = "/trabajadores/save", method = RequestMethod.POST)
    public String guardar(Trabajador t, BindingResult result, RedirectAttributes flash, @RequestParam("imagenform") MultipartFile multipart) throws Exception {
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                logger.info("Error: {}", error.getDefaultMessage());
            }
        }

        if (!multipart.isEmpty()){
            String ruta = this.PATH_IMG;
            String nombreImagen = Utileria.guardarArchivo(multipart, ruta);
            if (nombreImagen != null){
                t.setImagen(nombreImagen);
            }
        }

        String mensaje = (t.getId() !=null ) ? "Registro modificado correctamente." : "Registro creado exitosamente.";

        this.trabajadorService.registrar(t);
        flash.addFlashAttribute("success", mensaje);

        return "redirect:/trabajadores";
    }

    /* ELIMINAR */
    @RequestMapping(value = "/trabajadores/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id) throws Exception {
        this.trabajadorService.eliminar(id);
        return "redirect:/trabajadores";
    }

    /*METODO PARA MANEJAR FECHAS*/
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
