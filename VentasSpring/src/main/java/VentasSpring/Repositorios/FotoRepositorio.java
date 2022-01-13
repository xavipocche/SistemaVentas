package VentasSpring.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import VentasSpring.Entidades.Foto;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Xavier Pocchettino
 */
@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String>  {

}
