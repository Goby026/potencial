package uml.sc.potencial.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uml.sc.potencial.services.TrabajadorService;

@Controller
public class IndexController {

    @Value("${mi.config.title}")
    private String TITULO;

    @Value("${mi.config.code}")
    private String CODIGO;

    @Value("${mi.config.nombre}")
    private String NOMBRE;

    @Value("${mi.config.cargo}")
    private String CARGO;

//    @Value("${mi.config.ciudad}")
//    private String CIUDAD;
//
//    @Value("${mi.config.lista}")
//    private String LISTA;

    private final TrabajadorService service;

    public IndexController(TrabajadorService service) {
        this.service = service;
    }

    @RequestMapping("/datos")
    public String index(Model model){

        try {
            model.addAttribute("trabajadores", service.listar());
            model.addAttribute("cantidad", "total trabajadores: " + this.service.listar().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("title", this.TITULO);
        model.addAttribute("code", this.CODIGO);
        model.addAttribute("nombre", this.NOMBRE);
        model.addAttribute("cargo", this.CARGO);

        return "index";
    }

    @RequestMapping("/redirect")
    public String redireccionar(){
        return "redirect:/welcome";
    }

    @RequestMapping("/forward")
    public String forwardd(){
        return "forward:/welcome";
    }

    @RequestMapping("/parametros/{msg}")
    public String parametros(Model model, @PathVariable String msg){

        model.addAttribute("title", this.TITULO);
        model.addAttribute("msg", msg);
        return "pages/parametros";
    }


//    @RequestMapping("/values")
//    public Map<String, Object> propertiesValues(){
//
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("title", this.TITULO);
//        map.put("code", this.CODIGO);
//        map.put("nombre", this.NOMBRE);
//        map.put("cargo", this.CARGO);
//        map.put("ciudad", this.CIUDAD);
//        map.put("lista", this.LISTA);
//
//        return map;
//    }
}
