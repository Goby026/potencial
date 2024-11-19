package uml.sc.potencial.controllers;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uml.sc.potencial.entities.Horario;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HorarioController {

    @RequestMapping(value = {"/horario/save"}, method = RequestMethod.POST)
    public String formulario(Horario horario){
        System.out.println(horario.toString());
        return "redirect:/welcome";
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

}
