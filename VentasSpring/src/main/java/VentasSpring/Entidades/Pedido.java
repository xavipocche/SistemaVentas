package VentasSpring.Entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author Xavier Pocchettino
 */
@Entity
@Data
public class Pedido {
    
    @Id
    private String id;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Boolean alta;
    
    @OneToMany
    private List<Producto> productos;
}
