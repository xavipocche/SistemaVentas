package VentasSpring.Servicios;

import VentasSpring.Entidades.Producto;
import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Repositorios.ProductoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xavier Pocchettino
 */
@Service
public class ProductoServicio {
    
    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    @Transactional
    public void registrarProducto(String nombre, Double precio, String descripcion, 
            String fabricante, Integer stock){
        //Validar datos
        Producto producto = new Producto();
        
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setFabricante(fabricante);
        producto.setStock(stock);
        producto.setAlta(Boolean.TRUE);
        
        productoRepositorio.save(producto);
    }
    
    @Transactional
    public void modificarProducto(String id, String nombre, Double precio, String descripcion, 
            String fabricante, Integer stock) throws ErrorServicio{
        //Validar datos
        Optional<Producto> respuestaProducto = productoRepositorio.findById(id);
        if(respuestaProducto.isPresent()){
            Producto producto = respuestaProducto.get();
            
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setDescripcion(descripcion);
            producto.setFabricante(fabricante);
            producto.setStock(stock);
            
            productoRepositorio.save(producto);
        } else{
            throw new ErrorServicio("El producto solicitado no existe");
        }
    }
    
    @Transactional
    public void bajaProducto(String id) throws ErrorServicio{
        Optional<Producto> respuestaProducto = productoRepositorio.findById(id);
        if(respuestaProducto.isPresent()){
            Producto producto = respuestaProducto.get();
            
            producto.setAlta(Boolean.FALSE);
            
            productoRepositorio.save(producto);
        } else{
            throw new ErrorServicio("EL producto solicitado no existe");
        }
    }
    
    @Transactional
    public void altaProducto(String id) throws ErrorServicio{
        Optional<Producto> respuestaProducto = productoRepositorio.findById(id);
        if(respuestaProducto.isPresent()){
            Producto producto = respuestaProducto.get();
            
            producto.setAlta(Boolean.TRUE);
            
            productoRepositorio.save(producto);
        } else{
            throw new ErrorServicio("EL producto solicitado no existe");
        }
    }
}
