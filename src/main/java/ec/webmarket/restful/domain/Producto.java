package ec.webmarket.restful.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDate.now();
    }
}
