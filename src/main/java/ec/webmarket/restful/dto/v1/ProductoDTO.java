package ec.webmarket.restful.dto.v1;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductoDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Double precioUnitario;
    private Integer stock;
    private LocalDate fechaCreacion;
}
