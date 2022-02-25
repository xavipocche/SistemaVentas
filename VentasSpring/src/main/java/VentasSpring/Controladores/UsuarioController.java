package VentasSpring.Controladores;

import VentasSpring.Entidades.Usuario;
import VentasSpring.Servicios.UsuarioServicio;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Xavier Pocchettino
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registro")
    public String registroUsuario(ModelMap modelo) {

        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        return "registro-usuario.html";
    }

    @PostMapping("/registro")
    public String registroUsuario(@Valid Usuario usuario, Errors errores, ModelMap modelo, MultipartFile archivo) {
        try {

            if (errores.hasErrors()) {
                return "registro-usuario.html";
            }

            usuarioServicio.registrarUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getPassword(), usuario.getTelefono(), usuario.getSaldo(), archivo);

            return "index.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "registro-usuario.html";
        }

    }

    @GetMapping("/login")
    public String loginUsuario(ModelMap modelo, @RequestParam(required = false) String error, @RequestParam(required = false) String exito) {
        if(error != null){
            modelo.put("error", "Nombre de usuario o clave incorrectos");
        }
        return "login.html";
    }

}
