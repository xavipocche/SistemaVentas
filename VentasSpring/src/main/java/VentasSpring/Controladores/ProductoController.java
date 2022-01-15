package VentasSpring.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Xavier Pocchettino
 */
@Controller
@RequestMapping("/producto")
public class ProductoController {

    @GetMapping("/registro")
    public String registroProducto() {
        return "registro-producto";
    }

}
