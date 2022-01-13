package VentasSpring.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Xavier Pocchettino
 */
@Controller
@RequestMapping("/")
public class ControllerVistas {
    
    @GetMapping("/index")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/generic")
    public String generic(){
        return "generic.html";
    }
    
    @GetMapping("/elements")
    public String elements(){
        return "elements.html";
    }

}
