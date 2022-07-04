package VentasSpring.Entidades;

import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Nacho
 */
@Entity
@Data
public class Compra {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Date fechaCompra;
    
    @NotNull
    private Double monto;
    
    @NotEmpty
    private String estado;
    
    @NotEmpty
    private String direccion;
    
    @OneToMany
    private List<Usuario> cliente;
    
    @OneToOne
    private Pago pago;
}
