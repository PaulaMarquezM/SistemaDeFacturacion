package ec.webmarket.restful.dto.v1;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FacturaCabeceraDTO {
    private Long id;
    private String numeroFactura;
    private ClienteDTO cliente;
    private LocalDate fechaEmision;
    private Double subtotal;
    private Double impuestos;
    private Double total;
    private String estado;
}
