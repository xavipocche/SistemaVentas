package VentasSpring.Entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    
    //Es mejor crear un pedido y setearle un usuario que crear un usuario y setearle un pedido
    @OneToOne
    private Usuario usuario;
}
