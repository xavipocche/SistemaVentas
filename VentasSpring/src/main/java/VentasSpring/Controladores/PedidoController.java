package VentasSpring.Controladores;

import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Servicios.PedidoServicio;
import VentasSpring.Servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/catalogo")
    public String mostrarProductos(ModelMap modelo) {
        try {
            if (productoServicio.listarProductosDeAlta().size() == 0) {
                modelo.put("productos", null);
            } else {
                modelo.put("productos", productoServicio.listarProductosDeAlta());
            }
            
            return "marketplaceList.html";
        } catch (ErrorServicio ex) {
            return "index.html";
        }
    }
    // SI ESTO SE VA A USAR CREAR UN BOTÓN PARA ACCEDER AL DETALLE DEL PRODUCTO
//    @GetMapping("/producto/{id}")
//    public String mostrarProducto(@PathVariable String id, ModelMap modelo) {
//        try {
//            modelo.put("producto", productoServicio.buscarPorId(id));
//            return "producto";
//        } catch (ErrorServicio ex) {
//            return "index.html";
//        }
//    }
}