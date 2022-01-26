package VentasSpring.Servicios;

import VentasSpring.Entidades.Foto;
import VentasSpring.Entidades.Usuario;
import VentasSpring.Enums.Rol;
import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Xavier Pocchettino
 */
@Service
public class UsuarioServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuariorepositorio;
    
    @Autowired
    private FotoServicio fotoServicio;
    
    @Transactional
    public void registrarUsuario(String nombre, String apellido, String email, 
            String password, Long telefono, Double saldo, MultipartFile archivo) throws Exception{
        
        if (usuariorepositorio.buscarPorEmail(email) != null) {
            throw new Exception("Ya existe un usuario con ese email");
        }
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        
        String encriptacion = new BCryptPasswordEncoder().encode(password);
        usuario.setPassword(encriptacion);
        
        usuario.setTelefono(telefono);
        usuario.setAlta(Boolean.TRUE);
        usuario.setSaldo(saldo);
        usuario.setRol(Rol.USER);
        
        Foto foto = fotoServicio.guardarFoto(archivo);
        usuario.setFoto(foto);
        
        usuariorepositorio.save(usuario);
    }
    
    @Transactional
    public void modificarUsuario(String idusuario, String nombre, String apellido, String email, 
            String password, Long telefono) throws ErrorServicio{
        
        //Validar datos
        Optional<Usuario> respuestaUsuario = usuariorepositorio.findById(idusuario);
        if(respuestaUsuario.isPresent()){
            Usuario usuario = respuestaUsuario.get();
            
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setTelefono(telefono);
            
            usuariorepositorio.save(usuario);
        } else{
            throw new ErrorServicio("El usuario solicitado no existe");
        }
    }
    
    @Transactional
    public void bajaUsuario(String id) throws ErrorServicio{
        Optional<Usuario> respuestaUsuario = usuariorepositorio.findById(id);
        if(respuestaUsuario.isPresent()){
            Usuario usuario = respuestaUsuario.get();
            usuario.setAlta(Boolean.FALSE);
            
            usuariorepositorio.save(usuario);
        } else{
            throw new ErrorServicio("El usuario solicitado no existe");
        }
    }
    
    @Transactional
    public void altaUsuario(String id) throws ErrorServicio{
        Optional<Usuario> respuestaUsuario = usuariorepositorio.findById(id);
        if(respuestaUsuario.isPresent()){
            Usuario usuario = respuestaUsuario.get();
            usuario.setAlta(Boolean.TRUE);
            
            usuariorepositorio.save(usuario);
        } else{
            throw new ErrorServicio("El usuario solicitado no existe");
        }
    }
    
    public Usuario buscarPorId(String id) throws ErrorServicio{
        Optional<Usuario> respuestaUsuario = usuariorepositorio.findById(id);
        if(respuestaUsuario.isPresent()){
            Usuario usuario = respuestaUsuario.get();
            return usuario; 
        } else {
            throw new ErrorServicio("El usuario solicitado no existe");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuariorepositorio.buscarPorEmail(email);
        
        if(usuario != null){
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
            permisos.add(p1);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            
            User user = new User(usuario.getEmail(), usuario.getPassword(), permisos);
            
            return user;
        } else{
            return null;
        }
    }
    
    
}
