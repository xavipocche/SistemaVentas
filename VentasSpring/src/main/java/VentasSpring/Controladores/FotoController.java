
package VentasSpring.Controladores;

import VentasSpring.Entidades.Foto;
import VentasSpring.Entidades.Producto;
import VentasSpring.Entidades.Usuario;
import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Servicios.FotoServicio;
import VentasSpring.Servicios.ProductoServicio;
import VentasSpring.Servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nacho
 */
@Controller
@RequestMapping("/foto")
public class FotoController {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private ProductoServicio productoServicio;
    
    @Autowired
    private FotoServicio fotoServicio;
    
    @GetMapping("/usuario")
    public ResponseEntity<byte[]> fotoUsuario(String id) {
        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);

            byte[] foto = usuario.getFoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/producto")
    public ResponseEntity<byte[]> fotoProducto(String id) {
        try {
            Producto producto = productoServicio.buscarPorId(id);

            byte[] foto = producto.getFotos().get(0).getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/productos")
    public ResponseEntity<byte[]> fotoProductos(String id) {
        try {
            Foto fotoAux = fotoServicio.buscarPorId(id);
            
            byte[] foto = fotoAux.getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
