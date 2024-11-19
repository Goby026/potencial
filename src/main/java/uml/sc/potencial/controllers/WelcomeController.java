package uml.sc.potencial.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
public class WelcomeController {

    @Value("${mi.config.title}")
    public String TITLE;

    @RequestMapping({"/","/welcome"})
    public String welcome(Map<String, Object> map){

        map.put("title", this.TITLE);

        return "index";
    }
}
