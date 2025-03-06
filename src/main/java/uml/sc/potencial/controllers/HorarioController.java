package uml.sc.potencial.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import uml.sc.potencial.entities.Horario;
import uml.sc.potencial.services.HorarioService;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1")
public class HorarioController {

    private final HorarioService service;
    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    public HorarioController(HorarioService service) {
        this.service = service;
    }

    /*PAGINA INICIAL*/
//    @RequestMapping(value = "/horarios")
//    public String index(Model model) throws Exception{
//        List<Horario> horarios = this.service.listar();
//        String title = "HORARIOS";
//        model.addAttribute("title", title);
//        model.addAttribute("horarios", horarios);
//        return "pages/horarios/index";
//    }

    /* LISTAR TODOS LOS HORARIOS */
    @GetMapping("/horarios")
    public ResponseEntity<HashMap<String, List<Horario>>> listar() throws Exception {
        List<Horario> horarios = this.service.listar();
        HashMap<String, List<Horario>> resp = new HashMap<>();
        resp.put("horarios", horarios);
        return new ResponseEntity<HashMap<String, List<Horario>>>(resp, HttpStatus.OK);
    }

    /*CARGAR FORMULARIO*/
//    @RequestMapping(value = "/horarios/formulario", method = RequestMethod.GET)
//    public String create(Model model) throws Exception {
//        Horario horario = new Horario();
//        model.addAttribute("horario", horario);
//        model.addAttribute("title", "registrar horario".toUpperCase());
//        return "pages/horarios/formulario";
//    }

    /*CARGAR FORMULARIO PARA EDITAR*/
//    @RequestMapping(value = "/horarios/formulario/{id}", method = RequestMethod.GET)
//    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
//        Horario horario = null;
//        String title = "registrar nuevo horario";
//        if (id > 0) {
//            horario = this.service.obtener(id);
//            title = "modificar horario";
//            if (horario == null) {
//                flash.addFlashAttribute("error", "No se puede cargar el registro seleccionado 👀");
//            } else {
//                flash.addFlashAttribute("success", "Registro modificado correctamente.");
//            }
//        } else {
//            flash.addFlashAttribute("error", "No existe el id seleccionado.");
//            return "redirect:/horarios";
//        }
//
//        model.put("title", title.toUpperCase());
//        model.put("horario", horario);
//
//        return "pages/horarios/formulario";
//    }

    /*  OBTENER HORARIO POR ID */
    @GetMapping("/horarios/{id}")
    public ResponseEntity<Horario> obtener(@PathVariable(value = "id") Long id) throws Exception{
        try {
            Horario horario = this.service.obtener(id);
            return new ResponseEntity<Horario>(horario, HttpStatus.OK);
        }catch (NoSuchElementException ex){
            return new ResponseEntity<Horario>(HttpStatus.NOT_FOUND);
        }
    }

    /*PERSISTIR EN BASE DE DATOS (REGISTRAR/MODIFICAR)*/
//    @RequestMapping(value = "/horarios/save", method = RequestMethod.POST)
//    public String guardar(Horario t, BindingResult result, RedirectAttributes flash) throws Exception {
//        if (result.hasErrors()){
//            for (ObjectError error: result.getAllErrors()){
//                logger.info("Error: {}", error.getDefaultMessage());
//            }
//        }
//        String mensaje = (t.getId() !=null ) ? "Registro modificado correctamente." : "Registro creado exitosamente.";
//        this.service.registrar(t);
//        flash.addFlashAttribute("success", mensaje);
//
//        return "redirect:/horarios";
//    }

    /* REGISTRAR HORARIO */
    @PostMapping("/horarios")
    public ResponseEntity<Horario> registrar(@RequestBody Horario h) throws Exception {
        try {
            Horario horario = this.service.registrar(h);
            return new ResponseEntity<Horario>(horario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Horario>(HttpStatus.BAD_REQUEST);
        }
    }

    /* ACTUALIZAR HORARIO */
    @PutMapping("/horarios/{id}")
    public ResponseEntity<Horario> actualizar(@RequestBody Horario h, @PathVariable(value = "id") Long id) throws Exception {
        try {
            Horario horario = service.registrar(h);
            return new ResponseEntity<Horario>(horario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Horario>(HttpStatus.BAD_REQUEST);
        }
    }

    /* ELIMINAR */
    @DeleteMapping("/horarios/{id}")
    public ResponseEntity<Horario> borrar(@PathVariable(value = "id") Long id) throws Exception {
        try {
            this.service.eliminar(id);
            return new ResponseEntity<Horario>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Horario>(HttpStatus.BAD_REQUEST);
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

}
