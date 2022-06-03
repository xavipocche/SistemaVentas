package VentasSpring.Controladores;

import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Servicios.PedidoServicio;
import VentasSpring.Servicios.ProductoServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Xavier Pocchettino
 */
@Controller
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoServicio pedidoServicio;
    
    @Autowired
    private ProductoServicio productoServicio;

//    @GetMapping("/catalogo")
//    public String mostrarProductos(ModelMap modelo) {
//        try {
//            modelo.put("productos", productoServicio.listarProductos());
//            return "marketplace.html";
//        } catch (ErrorServicio ex) {
//            return "index.html";
//        }
//    }
    
    @GetMapping("/catalogo")
    public String mostrarProductos(ModelMap modelo) {
        try {
            modelo.put("productos", productoServicio.listarProductos());
            return "marketplaceList.html";
        } catch (ErrorServicio ex) {
            return "index.html";
        }
    }
    
    @GetMapping("/producto/{id}")
    public String mostrarProducto(@PathVariable String id, ModelMap modelo) {
        try {
            modelo.put("producto", productoServicio.buscarPorId(id));
            return "producto";
        } catch (ErrorServicio ex) {
            return "index.html";
        }
    }
}
