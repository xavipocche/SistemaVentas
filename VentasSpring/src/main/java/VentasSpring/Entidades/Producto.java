package VentasSpring.Entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Xavier Pocchettino
 */
@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;
    
    @NotEmpty
    private String nombre;
    
    @NotNull
    private Double precio;
    
    @NotEmpty
    private String descripcion;
    
    @NotEmpty
    private String fabricante;
    
    @NotNull
    private Integer stock;
    private Boolean alta;
    
    @OneToMany
    private List<Foto> foto;

}
