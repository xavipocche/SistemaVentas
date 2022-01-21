package VentasSpring.Servicios;

import VentasSpring.Entidades.Foto;
import VentasSpring.Entidades.Producto;
import VentasSpring.Errores.ErrorServicio;
import VentasSpring.Repositorios.ProductoRepositorio;
import java.util.ArrayList;
import java.util.List;
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
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public void registrarProducto(String nombre, Double precio, String descripcion,
            String fabricante, Integer stock, List<MultipartFile> archivos) throws Exception {

        if (productoRepositorio.buscarPorNombre(nombre) != null) {
            throw new Exception("Ya existe un producto con ese nombre.");
        }

        Producto producto = new Producto();

        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setFabricante(fabricante);
        producto.setStock(stock);
        producto.setAlta(Boolean.TRUE);

        /*Recibo la lista de archivos(fotos), creo una lista local, recorro la lista que recibo guardo cada foto y
        la añado en la lista local. Como último paso le seteo a "producto" la lista local con todas las fotos
        guardadas. En caso de estar vacía la lista, se lanza una Exception requeriendo cargar al menos 1 foto
         */
        List<Foto> listaFotos = new ArrayList();
        for (MultipartFile auxArchivo : archivos) {
            listaFotos.add(fotoServicio.guardarFoto(auxArchivo));
        }

        if (!validarImagenNula(listaFotos)) {
            throw new Exception("Por favor, cargue al menos 1 imagen del producto.");
        }

        producto.setFotos(listaFotos);

        productoRepositorio.save(producto);
    }

    //Valida que no se cargue la lista nula, ya que es necesario que el producto se cargue con imagenes
    public Boolean validarImagenNula(List<Foto> listaFotos) throws Exception {

        for (Foto auxArchivos : listaFotos) {
            if (auxArchivos == null) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Transactional
    public void modificarProducto(String id, String nombre, Double precio, String descripcion,
            String fabricante, Integer stock) throws ErrorServicio {
        //Validar datos
        Optional<Producto> respuestaProducto = productoRepositorio.findById(id);
        if (respuestaProducto.isPresent()) {
            Producto producto = respuestaProducto.get();

            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setDescripcion(descripcion);
            producto.setFabricante(fabricante);
            producto.setStock(stock);

            productoRepositorio.save(producto);
        } else {
            throw new ErrorServicio("El producto solicitado no existe");
        }
    }

    @Transactional
    public void bajaProducto(String id) throws ErrorServicio {
        Optional<Producto> respuestaProducto = productoRepositorio.findById(id);
        if (respuestaProducto.isPresent()) {
            Producto producto = respuestaProducto.get();

            producto.setAlta(Boolean.FALSE);

            productoRepositorio.save(producto);
        } else {
            throw new ErrorServicio("EL producto solicitado no existe");
        }
    }

    @Transactional
    public void altaProducto(String id) throws ErrorServicio {
        Optional<Producto> respuestaProducto = productoRepositorio.findById(id);
        if (respuestaProducto.isPresent()) {
            Producto producto = respuestaProducto.get();

            producto.setAlta(Boolean.TRUE);

            productoRepositorio.save(producto);
        } else {
            throw new ErrorServicio("EL producto solicitado no existe");
        }
    }
}
