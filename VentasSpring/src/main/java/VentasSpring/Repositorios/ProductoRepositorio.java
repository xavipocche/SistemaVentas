package VentasSpring.Repositorios;

import VentasSpring.Entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Pocchettino
 */
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {
    
    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    public Producto buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.alta = 1 ORDER BY p.nombre")
    public List<Producto> listarProductosDeAlta();
    
    @Query("SELECT p FROM Producto p ORDER BY p.nombre")
    public List<Producto> listarProductosAll();
    
    @Query("SELECT p from Producto p ORDER BY p.precio")
    public List<Producto> ordenarProductosPrecio();

}
