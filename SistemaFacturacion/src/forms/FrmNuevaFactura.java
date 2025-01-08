package forms;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import ec.edu.puce.facturacion.Cliente;
import ec.edu.puce.facturacion.Factura;
import ec.edu.puce.facturacion.Producto;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrmNuevaFactura extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    private Factura facturaCreada;
    private FacturaListener facturarListener;

    private JTextField txtNumeroFactura, txtFecha, txtCedulaCliente, txtNombreCliente, txtApellidoCliente, txtTelefono, txtEmail, txtDireccion;
    private JTextField txtCodigoProducto, txtNombreProducto, txtCantidad, txtPrecio;

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    private JLabel lblSubtotal, lblIVA, lblTotal;

    private Cliente cliente;
    private List<Producto> productos = new ArrayList<>();

    public FrmNuevaFactura(Factura factura) {
        super("Factura", true, true, true, true);
        setSize(800, 600);
        setLocation(100, 100);
        setLayout(new BorderLayout());
        inicializarComponentes();

        if (factura != null) {
            txtNumeroFactura.setText(String.valueOf(factura.getNumero()));
            txtFecha.setText(factura.getEmision());
            cliente = factura.getCliente();

            txtCedulaCliente.setText(cliente.getCedula());
            txtNombreCliente.setText(cliente.getNombre());
            txtApellidoCliente.setText(cliente.getApellido());
            txtTelefono.setText(cliente.getTelefono());
            txtEmail.setText(cliente.getEmail());
            txtDireccion.setText(cliente.getDireccion());

            for (Producto producto : factura.getProductos()) {
                modeloTabla.addRow(new Object[]{
                        modeloTabla.getRowCount() + 1,
                        producto.getCodigo(),
                        producto.getNombre(),
                        producto.getCantidad(),
                        producto.getPrecio(),
                        producto.getCantidad() * producto.getPrecio()
                });
            }

            actualizarTotales();
        }
    }

    private void inicializarComponentes() {
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));

        
        JPanel panelFactura = new JPanel(new GridLayout(1, 4));
        panelFactura.add(new JLabel("Factura No."));
        txtNumeroFactura = new JTextField();
        panelFactura.add(txtNumeroFactura);

        panelFactura.add(new JLabel("Fecha"));
        txtFecha = new JTextField();
        panelFactura.add(txtFecha);
        panelSuperior.add(panelFactura);

    
        JPanel panelCliente = new JPanel(new GridLayout(2, 6));
        panelCliente.setBorder(BorderFactory.createTitledBorder("Cliente"));

        panelCliente.add(new JLabel("Cédula"));
        txtCedulaCliente = new JTextField();
        panelCliente.add(txtCedulaCliente);
        JButton btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        panelCliente.add(btnBuscarCliente);

        panelCliente.add(new JLabel("Nombres"));
        txtNombreCliente = new JTextField();
        txtNombreCliente.setEditable(false);
        panelCliente.add(txtNombreCliente);

        panelCliente.add(new JLabel("Apellidos"));
        txtApellidoCliente = new JTextField();
        txtApellidoCliente.setEditable(false);
        panelCliente.add(txtApellidoCliente);

        panelCliente.add(new JLabel("Teléfono"));
        txtTelefono = new JTextField();
        txtTelefono.setEditable(false);
        panelCliente.add(txtTelefono);

        panelCliente.add(new JLabel("Email"));
        txtEmail = new JTextField();
        txtEmail.setEditable(false);
        panelCliente.add(txtEmail);

        panelCliente.add(new JLabel("Dirección"));
        txtDireccion = new JTextField();
        txtDireccion.setEditable(false);
        panelCliente.add(txtDireccion);

        panelSuperior.add(panelCliente);

        add(panelSuperior, BorderLayout.NORTH);

        
        JPanel panelProductos = new JPanel(new BorderLayout());
        panelProductos.setBorder(BorderFactory.createTitledBorder("Producto"));

        JPanel panelProductoFormulario = new JPanel(new GridLayout(1, 7));
        panelProductoFormulario.add(new JLabel("Código"));
        txtCodigoProducto = new JTextField();
        panelProductoFormulario.add(txtCodigoProducto);
        JButton btnBuscarProducto = new JButton("Buscar");
        btnBuscarProducto.addActionListener(e -> buscarProducto());
        panelProductoFormulario.add(btnBuscarProducto);

        panelProductoFormulario.add(new JLabel("Nombre"));
        txtNombreProducto = new JTextField();
        txtNombreProducto.setEditable(false);
        panelProductoFormulario.add(txtNombreProducto);

        panelProductoFormulario.add(new JLabel("Cantidad"));
        txtCantidad = new JTextField();
        panelProductoFormulario.add(txtCantidad);

        panelProductoFormulario.add(new JLabel("Precio"));
        txtPrecio = new JTextField();
        txtPrecio.setEditable(false);
        panelProductoFormulario.add(txtPrecio);

        JButton btnAgregarProducto = new JButton("Agregar");
        btnAgregarProducto.addActionListener(e -> agregarProducto());
        panelProductoFormulario.add(btnAgregarProducto);

        panelProductos.add(panelProductoFormulario, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Item", "Código", "Producto", "Cantidad", "Precio", "Subtotal"}, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaProductos);
        panelProductos.add(scrollTabla, BorderLayout.CENTER);

        JPanel panelTotales = new JPanel(new GridLayout(3, 2));
        panelTotales.add(new JLabel("Subtotal"));
        lblSubtotal = new JLabel("0.00");
        panelTotales.add(lblSubtotal);

        panelTotales.add(new JLabel("IVA"));
        lblIVA = new JLabel("0.00");
        panelTotales.add(lblIVA);

        panelTotales.add(new JLabel("Total"));
        lblTotal = new JLabel("0.00");
        panelTotales.add(lblTotal);

        panelProductos.add(panelTotales, BorderLayout.SOUTH);

        add(panelProductos, BorderLayout.CENTER);

        JButton btnGuardarFactura = new JButton("Guardar Factura");
        btnGuardarFactura.addActionListener(e -> guardarFactura());
        add(btnGuardarFactura, BorderLayout.SOUTH);
    }

    private void buscarCliente() {
        String cedula = txtCedulaCliente.getText();
        List<Cliente> listaClientes = obtenerListaClientes(); 
        cliente = listaClientes.stream()
                .filter(c -> c.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            txtNombreCliente.setText(cliente.getNombre());
            txtApellidoCliente.setText(cliente.getApellido());
            txtTelefono.setText(cliente.getTelefono());
            txtEmail.setText(cliente.getEmail());
            txtDireccion.setText(cliente.getDireccion());
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposCliente();
        }
    }

    private void buscarProducto() {
        String c = txtCodigoProducto.getText();
        List<Producto> listaProductos = obtenerListaProductos();
        Producto productoEncontrado = listaProductos.stream()
                .filter(p -> p.getCodigo().equals(c))
                .findFirst()
                .orElse(null);

        if (productoEncontrado != null) {
            txtNombreProducto.setText(productoEncontrado.getNombre());
            txtPrecio.setText(String.valueOf(productoEncontrado.getPrecio()));
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposProducto();
        }
    }

    private void limpiarCamposCliente() {
        txtNombreCliente.setText("");
        txtApellidoCliente.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
    }

    private void limpiarCamposProducto() {
        txtNombreProducto.setText("");
        txtPrecio.setText("");
    }


    private List<Cliente> obtenerListaClientes() {
        for (JInternalFrame frame : getDesktopPane().getAllFrames()) {
            if (frame instanceof FrmListaClientes) {
                return ((FrmListaClientes) frame).getListaClientes();
            }
        }
        return new ArrayList<>(); 
    }

    private List<Producto> obtenerListaProductos() {
        for (JInternalFrame frame : getDesktopPane().getAllFrames()) {
            if (frame instanceof FrmListaProductos) {
                return ((FrmListaProductos) frame).getListaProductos();
            }
        }
        return new ArrayList<>(); 
    }
    private void agregarProducto() {
        String codigo = txtCodigoProducto.getText();
        String nombre = txtNombreProducto.getText();
        String cantidadStr = txtCantidad.getText();
        String precioStr = txtPrecio.getText();

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            double precio = Double.parseDouble(precioStr);
            double subtotal = cantidad * precio;

            Producto producto = new Producto(codigo, nombre, cantidad, precio);
            productos.add(producto);

            modeloTabla.addRow(new Object[]{modeloTabla.getRowCount() + 1, codigo, nombre, cantidad, precio, subtotal});

            actualizarTotales();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad o precio inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTotales() {
        double subtotal = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            subtotal += (double) modeloTabla.getValueAt(i, 5);
        }

        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        lblSubtotal.setText(String.format("%.2f", subtotal));
        lblIVA.setText(String.format("%.2f", iva));
        lblTotal.setText(String.format("%.2f", total));
    }

    private void guardarFactura() {
        if (cliente != null && !productos.isEmpty()) {
            try {
                int numeroFactura = Integer.parseInt(txtNumeroFactura.getText());
                String fecha = txtFecha.getText();

                facturaCreada = new Factura(numeroFactura, fecha, cliente, productos);

                JOptionPane.showMessageDialog(this, "Factura guardada con éxito. Total: " + facturaCreada.getTotal());

                if (facturarListener != null) {
                    facturarListener.onFacturaGenerada(facturaCreada);
                }

                dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Número de factura inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
        }
    }

    public void setFacturarListener(FacturaListener listener) {
        this.facturarListener = listener;
    }

    public interface FacturaListener {
        void onFacturaGenerada(Factura factura);
    }
}
