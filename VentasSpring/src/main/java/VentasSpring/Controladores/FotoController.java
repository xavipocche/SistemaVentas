
package VentasSpring.Controladores;

import VentasSpring.Entidades.Usuario;
import VentasSpring.Errores.ErrorServicio;
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
    
    @GetMapping("/usuario")
    public ResponseEntity<byte[]> fotoUsuario(String id) {
        try {
            Usuario usuario = usuarioServicio.buscarPorId(id);

            byte[] foto = usuario.getFoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
