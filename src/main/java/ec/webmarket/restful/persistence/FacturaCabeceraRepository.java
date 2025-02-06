package ec.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.webmarket.restful.domain.FacturaCabecera;

public interface FacturaCabeceraRepository extends JpaRepository<FacturaCabecera, Long> {
}
