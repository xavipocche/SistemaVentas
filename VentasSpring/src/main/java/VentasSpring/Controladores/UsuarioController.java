package VentasSpring.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Xavier Pocchettino
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @GetMapping("/registro")
    public String registroUsuario(){
        return "registro-usuario.html";
    }
    
}
