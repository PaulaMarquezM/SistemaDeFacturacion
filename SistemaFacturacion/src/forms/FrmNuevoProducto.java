package forms;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import ec.edu.puce.facturacion.Producto;
public class FrmNuevoProducto extends JDialog { 
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtCodigo;
    private JTextField txtStock;
    private JTextField txtPrecio;
    private Producto productoEditar;
    private FrmListaProductos listaProductos;

    public FrmNuevoProducto(FrmListaProductos parent, Producto producto) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), true);
        this.listaProductos = parent;
        this.productoEditar = producto;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(producto == null ? "Nuevo Producto" : "Editar Producto");
        setBounds(100, 100, 450, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        crearCampos();

        if (producto != null) {
            cargarDatosProducto();
        }

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarProducto());
        buttonPane.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            listaProductos.resetVentanaProducto();
            dispose();
        });
        buttonPane.add(btnCancelar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                listaProductos.resetVentanaProducto();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                listaProductos.resetVentanaProducto();
            }
        });
    }

    private void crearCampos() {
        crearCampo("Nombre:", txtNombre = new JTextField(), 20, 70);
        crearCampo("Stock:", txtStock = new JTextField(), 20, 110);
        crearCampo("Código:", txtCodigo = new JTextField(), 20, 150);
        crearCampo("Precio:", txtPrecio = new JTextField(), 20, 190);
   
    }

    private void crearCampo(String etiqueta, JTextField campo, int x, int y) {
        JLabel label = new JLabel(etiqueta);
        label.setBounds(x, y, 80, 25);
        contentPanel.add(label);

        campo.setBounds(x + 90, y, 250, 25);
        contentPanel.add(campo);
    }

    private void cargarDatosProducto() {
        txtStock.setText(String.valueOf(productoEditar.getStock()));
        txtNombre.setText(productoEditar.getNombre());
        txtCodigo.setText(productoEditar.getCodigo());
        txtPrecio.setText(String.valueOf(productoEditar.getPrecio()));
    }

    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty() || 
            txtNombre.getText().trim().isEmpty() || 
            txtStock.getText().trim().isEmpty() || 
            txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Todos los campos son obligatorios.",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(txtStock.getText().trim());
            Double.parseDouble(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Stock debe ser un número entero y Precio debe ser un número decimal.",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void guardarProducto() {
        if (!validarCampos()) return;
        try {
            Producto producto = new Producto(
                    txtCodigo.getText().trim(),
                    txtNombre.getText().trim(),
                    Integer.parseInt(txtStock.getText().trim()),
                    Double.parseDouble(txtPrecio.getText().trim())
                    
            );
            if (productoEditar == null) {
                listaProductos.cargarDatosEnTabla(producto);
            } else {
                listaProductos.actualizarDatosEnTabla(producto);
            }
            listaProductos.resetVentanaProducto();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar el producto: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}