package VentasSpring.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Nacho
 */
@Entity
@Data
public class Pago {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    private Double monto;
}
