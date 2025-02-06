package ec.webmarket.restful.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.dto.v1.FacturaCabeceraDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.FacturaCabeceraService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/factura-cabecera")
public class FacturaCabeceraController {

    @Autowired
    private FacturaCabeceraService entityService;

    // Obtener todas las facturas
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, entityService.findAll(new FacturaCabeceraDTO())), HttpStatus.OK);
    }

    // Obtener una factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        FacturaCabeceraDTO dto = new FacturaCabeceraDTO();
        dto.setId(id);
        Optional<FacturaCabeceraDTO> factura = entityService.find(dto).map(entityService::mapToDto);
        if (factura.isPresent()) {
            return new ResponseEntity<>(new ApiResponseDTO<>(true, factura.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponseDTO<>(false, "Factura no encontrada"), HttpStatus.NOT_FOUND);
    }

    // Crear una nueva factura
    @PostMapping
    public ResponseEntity<?> create(@RequestBody FacturaCabeceraDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, entityService.create(dto)), HttpStatus.CREATED);
    }

    // Actualizar una factura existente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FacturaCabeceraDTO dto) {
        dto.setId(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, entityService.update(dto)), HttpStatus.OK);
    }

    // Eliminar una factura por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        FacturaCabeceraDTO dto = new FacturaCabeceraDTO();
        dto.setId(id);
        entityService.delete(dto);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Factura eliminada correctamente"), HttpStatus.OK);
    }
}
