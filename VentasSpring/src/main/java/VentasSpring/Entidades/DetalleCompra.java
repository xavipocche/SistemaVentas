package VentasSpring.Entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Nacho
 */
@Entity
@Data
public class DetalleCompra {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    private Integer cantidad;
    
    @NotNull
    private Double precioCompra;
    
    @OneToMany
    private List<Producto> producto;
    
    @OneToMany
    private List<Compra> compra;
}
