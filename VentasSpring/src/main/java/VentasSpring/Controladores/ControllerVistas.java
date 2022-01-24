package VentasSpring.Controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/generic")
    public String generic(){
        return "generic.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/elements")
    public String elements(){
        return "elements.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String loginSuccesed(ModelMap modelo) {
        return "index.html";
    }
}
