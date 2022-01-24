package VentasSpring.Controladores;

import VentasSpring.Entidades.Producto;
import VentasSpring.Servicios.ProductoServicio;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Xavier Pocchettino
 */
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/registro")
    public String registroProducto(ModelMap modelo) {

        Producto producto = new Producto();
        modelo.addAttribute("producto", producto);
        return "registro-producto.html";

    }

    @PostMapping("/registro")
    public String registroProducto(@Valid Producto producto, Errors errores, ModelMap modelo, List<MultipartFile> archivos) {
        try {

            if (errores.hasErrors()) {
                return "registro-producto.html";
            }

            productoServicio.registrarProducto(producto.getNombre(), producto.getPrecio(), producto.getDescripcion(), producto.getFabricante(), producto.getStock(), archivos);

            return "index.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "registro-producto.html";
        }

    }
}
