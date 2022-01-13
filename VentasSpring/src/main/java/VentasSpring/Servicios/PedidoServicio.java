package VentasSpring.Servicios;

import VentasSpring.Entidades.Pedido;
import VentasSpring.Entidades.Usuario;
import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Repositorios.PedidoRepositorio;
import VentasSpring.Repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xavier Pocchettino
 */
@Service
public class PedidoServicio {
    
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void registrarPedido(String idusuario){
        //Validar datos
        //La idea es crear un pedido a nombre del usuario y luego agregarle productos con PathVariable
        Pedido pedido = new Pedido();
        Usuario usuario = usuarioRepositorio.getOne(idusuario);
        
        pedido.setUsuario(usuario);
        pedido.setAlta(Boolean.FALSE);
        pedido.setFecha(new Date());
        
        pedidoRepositorio.save(pedido);
    }
    
    @Transactional
    public void modificarPedido(String id, String idusuario) throws ErrorServicio{
        
        //Validar datos
        Optional<Pedido> respuestaPedido = pedidoRepositorio.findById(id);
        Optional<Usuario> respuestaUsuario = usuarioRepositorio.findById(idusuario);
        
        if(respuestaPedido.isPresent() && respuestaUsuario.isPresent()){
            Pedido pedido = respuestaPedido.get();
            Usuario usuario = respuestaUsuario.get();
            
            pedido.setUsuario(usuario);
            
            pedidoRepositorio.save(pedido);
        } else{
            throw new ErrorServicio("El pedido o usuario no existen");
        }
    }
    
    @Transactional
    public void bajaPedido(String id) throws ErrorServicio{
        Optional<Pedido> respuestaPedido = pedidoRepositorio.findById(id);
        if(respuestaPedido.isPresent()){
            Pedido pedido = respuestaPedido.get();
            pedido.setAlta(Boolean.FALSE);
            
            pedidoRepositorio.save(pedido);
        } else{
            throw new ErrorServicio("El pedido solicitado no existe");
        }
    }
    
    @Transactional
    public void altaPedido(String id) throws ErrorServicio{
        Optional<Pedido> respuestaPedido = pedidoRepositorio.findById(id);
        if(respuestaPedido.isPresent()){
            Pedido pedido = respuestaPedido.get();
            pedido.setAlta(Boolean.TRUE);
            
            pedidoRepositorio.save(pedido);
        } else{
            throw new ErrorServicio("El pedido solicitado no existe");
        }
    }
}
