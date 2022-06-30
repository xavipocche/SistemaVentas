package VentasSpring.Controladores;

import VentasSpring.Entidades.Usuario;
import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/perfil{id}")
    public String perfilUsuario(HttpSession session, ModelMap modelo, @RequestParam String id) {
        try {
            modelo.put("usuario", usuarioServicio.buscarPorId(id));
            return "perfil.html";
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "elements.html";
        }

    }

    @GetMapping("/modificar{id}")
    public String modificarUsuario(HttpSession session, ModelMap modelo, @RequestParam String id) {
        try {
            modelo.put("usuario", usuarioServicio.buscarPorId(id));
            return "modificar-usuario.html";
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            return "elements.html";
        }

    }

    @PostMapping("/modificar/{id}")
    public String modificarUsuario(Usuario usuario, Errors errores, ModelMap modelo) {
        try {

            if (errores.hasErrors()) {
                return "modificar-usuario.html";
            }
//            Usuario user = usuarioServicio.buscarPorId(usuario.getId());
//            System.out.println(user.getNombre());
            
//            if (usuarioServicio.buscarPorId(usuario.getId()) != null) {
//                System.out.println("ENCONTRADO");
//            } else {
//                System.out.println("no encontrado");
//            }

//            Usuario user = usuarioServicio.buscarPorId(id);
//            System.out.println("...............");
//            System.out.println(user.getNombre());
            usuarioServicio.modificarUsuario(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getTelefono());
            return "index.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelo.put("error", e.getMessage());
            return "modificar-usuario.html";
        }

    }

    @GetMapping("/login")
    public String loginUsuario(ModelMap modelo, @RequestParam(required = false) String error, @RequestParam(required = false) String exito) {
        if (error != null) {
            modelo.put("error", "Nombre de usuario o clave incorrectos");
        }
        return "login.html";
    }

}
