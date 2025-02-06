package ec.webmarket.restful.dto.v1;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String correoElectronico;
    private String direccion;
    private String telefono;
    private LocalDate fechaCreacion;
}
