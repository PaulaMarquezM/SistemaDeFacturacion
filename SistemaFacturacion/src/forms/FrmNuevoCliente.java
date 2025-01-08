package forms;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.facturacion.Cliente;
import ec.edu.puce.facturacion.Factura;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrmNuevoCliente extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private FrmListaClientes listaClientes;
    private Cliente clienteEditar;

    public FrmNuevoCliente(FrmListaClientes parent, Cliente cliente) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), true);
        this.listaClientes = parent;
        this.clienteEditar = cliente;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(cliente == null ? "Nuevo Cliente" : "Editar Cliente");
        setBounds(100, 100, 450, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

<<<<<<< HEAD
=======
        
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
        crearCampos();
<<<<<<< HEAD

=======
        
        
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
        if (cliente != null) {
            cargarDatosCliente();
        }

<<<<<<< HEAD
=======
       
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

<<<<<<< HEAD
=======
      
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarCliente());
        buttonPane.add(btnGuardar);

<<<<<<< HEAD
=======
      
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            listaClientes.resetVentanaCliente();
            dispose();
        });
        buttonPane.add(btnCancelar);

<<<<<<< HEAD
=======
       
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                listaClientes.resetVentanaCliente();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                listaClientes.resetVentanaCliente();
            }
        });
    }

    private void crearCampos() {
        crearCampo("Cédula:", txtCedula = new JTextField(), 20, 30);
        crearCampo("Nombre:", txtNombre = new JTextField(), 20, 70);
        crearCampo("Apellido:", txtApellido = new JTextField(), 20, 110);
        crearCampo("Dirección:", txtDireccion = new JTextField(), 20, 150);
        crearCampo("Teléfono:", txtTelefono = new JTextField(), 20, 190);
        crearCampo("Email:", txtEmail = new JTextField(), 20, 230);
    }

    private void crearCampo(String etiqueta, JTextField campo, int x, int y) {
        JLabel label = new JLabel(etiqueta);
        label.setBounds(x, y, 80, 25);
        contentPanel.add(label);

        campo.setBounds(x + 90, y, 250, 25);
        contentPanel.add(campo);
    }

    private void cargarDatosCliente() {
        txtCedula.setText(clienteEditar.getCedula());
        txtNombre.setText(clienteEditar.getNombre());
        txtApellido.setText(clienteEditar.getApellido());
        txtDireccion.setText(clienteEditar.getDireccion());
        txtTelefono.setText(clienteEditar.getTelefono());
        txtEmail.setText(clienteEditar.getEmail());
    }

    private boolean validarCampos() {
        if (txtCedula.getText().trim().isEmpty() || 
            txtNombre.getText().trim().isEmpty() || 
            txtApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Los campos Cédula, Nombre y Apellido son obligatorios",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void guardarCliente() {
        if (!validarCampos()) return;

        try {
            Cliente cliente = new Cliente(
                txtCedula.getText().trim(),
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtDireccion.getText().trim(),
                txtTelefono.getText().trim(),
                txtEmail.getText().trim()
            );

            if (clienteEditar == null) {
                listaClientes .cargarDatosEnTabla(cliente);
            } else {
                listaClientes.actualizarDatosEnTabla(cliente);
            }

            listaClientes.resetVentanaCliente();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al guardar el cliente: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}