package VentasSpring.Servicios;

import VentasSpring.Entidades.Foto;
import VentasSpring.Entidades.Usuario;
import VentasSpring.Enums.Rol;
import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Repositorios.UsuarioRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Xavier Pocchettino
 */
@Service
public class UsuarioServicio {
    
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
        usuario.setPassword(password);
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
}
