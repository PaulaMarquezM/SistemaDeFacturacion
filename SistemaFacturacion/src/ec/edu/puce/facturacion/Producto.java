package ec.edu.puce.facturacion;

public class Producto {
    private String codigo;
    private String nombre;
    private int stock;
    private double precio;
    private int cantidad;

    public Producto(String codigo, String nombre, int stock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public Producto() {
        
        this.codigo = "";
        this.nombre = "";
        this.stock = 0;
        this.precio = 0;
        this.cantidad= 0;
      
    }

    
    public String getCodigo() { return codigo; }
    
    public void setCodigo(String codigo) { this.codigo =codigo; }
    
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public double getPrecio() { return precio; }
    
    public void setPrecio(double precio) { this.precio = precio; }
    
    public int getStock() { return stock; }
    
    public void setStock(int stock) { this.stock = stock; }
    
    public int getCantidad() { return cantidad; }
    
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
}
