package ec.webmarket.restful.service.crud;

import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.FacturaCabecera;
import ec.webmarket.restful.domain.Cliente;
import ec.webmarket.restful.dto.v1.FacturaCabeceraDTO;
import ec.webmarket.restful.dto.v1.ClienteDTO;
import ec.webmarket.restful.persistence.FacturaCabeceraRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FacturaCabeceraService extends GenericCrudServiceImpl<FacturaCabecera, FacturaCabeceraDTO> {

    @Autowired
    private FacturaCabeceraRepository repository;

    @Override
    public FacturaCabecera mapToDomain(FacturaCabeceraDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de FacturaCabecera no puede ser nulo.");
        }

        FacturaCabecera factura = new FacturaCabecera();
        factura.setId(dto.getId());
        factura.setNumeroFactura(dto.getNumeroFactura());
        factura.setCliente(dto.getCliente() != null ? mapClienteDtoToEntity(dto.getCliente()) : null);
        factura.setFechaEmision(dto.getFechaEmision());
        factura.setSubtotal(dto.getSubtotal());
        factura.setImpuestos(dto.getImpuestos());
        factura.setTotal(dto.getTotal());
        factura.setEstado(dto.getEstado());

        return factura;
    }

    @Override
    public FacturaCabeceraDTO mapToDto(FacturaCabecera entity) {
        FacturaCabeceraDTO dto = new FacturaCabeceraDTO();
        dto.setId(entity.getId());
        dto.setNumeroFactura(entity.getNumeroFactura());
        dto.setCliente(entity.getCliente() != null ? mapClienteEntityToDto(entity.getCliente()) : null);
        dto.setFechaEmision(entity.getFechaEmision());
        dto.setSubtotal(entity.getSubtotal());
        dto.setImpuestos(entity.getImpuestos());
        dto.setTotal(entity.getTotal());
        dto.setEstado(entity.getEstado());

        return dto;
    }

    @Override
    public Optional<FacturaCabecera> find(FacturaCabeceraDTO dto) {
        if (dto.getId() == null) {
            return Optional.empty();
        }
        return repository.findById(dto.getId());
    }

    // Método para convertir ClienteDTO a Cliente
    private Cliente mapClienteDtoToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setIdentificacion(clienteDTO.getIdentificacion());
        cliente.setCorreoElectronico(clienteDTO.getCorreoElectronico());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        return cliente;
    }

    // Método para convertir Cliente a ClienteDTO
    private ClienteDTO mapClienteEntityToDto(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setIdentificacion(cliente.getIdentificacion());
        clienteDTO.setCorreoElectronico(cliente.getCorreoElectronico());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setTelefono(cliente.getTelefono());
        return clienteDTO;
    }

    public List<FacturaCabeceraDTO> findAllFacturas() {
        List<FacturaCabecera> facturas = repository.findAll();
        return facturas.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public FacturaCabeceraDTO findFacturaById(Long id) {
        Optional<FacturaCabecera> factura = repository.findById(id);
        if (!factura.isPresent()) {
            throw new IllegalArgumentException("No se encontró una factura con el ID: " + id);
        }
        return mapToDto(factura.get());
    }

    public FacturaCabeceraDTO createFactura(FacturaCabeceraDTO dto) {
        FacturaCabecera factura = mapToDomain(dto);
        FacturaCabecera savedFactura = repository.save(factura);
        return mapToDto(savedFactura);
    }

    public FacturaCabeceraDTO updateFactura(Long id, FacturaCabeceraDTO dto) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró una factura con el ID: " + id);
        }

        FacturaCabecera facturaExistente = repository.findById(id).get();
        facturaExistente.setNumeroFactura(dto.getNumeroFactura() != null ? dto.getNumeroFactura() : facturaExistente.getNumeroFactura());
        facturaExistente.setFechaEmision(dto.getFechaEmision() != null ? dto.getFechaEmision() : facturaExistente.getFechaEmision());
        facturaExistente.setSubtotal(dto.getSubtotal() != null ? dto.getSubtotal() : facturaExistente.getSubtotal());
        facturaExistente.setImpuestos(dto.getImpuestos() != null ? dto.getImpuestos() : facturaExistente.getImpuestos());
        facturaExistente.setTotal(dto.getTotal() != null ? dto.getTotal() : facturaExistente.getTotal());
        facturaExistente.setEstado(dto.getEstado() != null ? dto.getEstado() : facturaExistente.getEstado());

        FacturaCabecera updatedFactura = repository.save(facturaExistente);
        return mapToDto(updatedFactura);
    }

    public void deleteFacturaById(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró una factura con el ID: " + id);
        }

        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalStateException("No se puede eliminar la factura porque tiene registros asociados.");
        }
    }
}
