package ec.edu.puce.facturacion;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int numeroFactura;
    private String fecha;
    private Cliente cliente;
    private List<Producto> productos;
    private double totalFactura;

    public Factura(int numeroFactura, String fecha, Cliente cliente, List<Producto> productos) {
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.productos = productos;
        this.totalFactura = calculateTotal();
    }
    
    private double calculateTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio() * producto .getStock();
        }
        return total;
    }

    public int getNumero() {
    	return numeroFactura;
    }
    public void setNumero(int numero) { this.numeroFactura = numero; }

    public String getEmision() {
        return fecha;
    }
    public void setEmision (String fecha) {this.fecha = fecha;}

    public Cliente getCliente() {
        return cliente;
    }
    
    public void SetCliente (Cliente cliente) {this.cliente = cliente;}

    public double getSubTotal() {
        return totalFactura / 1.12;
    }
   

    public double getIVA() {
        return totalFactura - getSubTotal();
    }

    public double getTotal() {
        return totalFactura;
    }

	public List<Producto> getProductos() {
		return productos;
	}
	
	public void SetProductos(List<Producto> productos) { this.productos = productos;}
		
    }