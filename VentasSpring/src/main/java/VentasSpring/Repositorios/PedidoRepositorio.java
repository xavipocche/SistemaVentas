package VentasSpring.Repositorios;

import VentasSpring.Entidades.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Pocchettino
 */
@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, String> {
    
    @Query("SELECT p FROM Pedido p")
    public List<Pedido> listarPedidos();

}
