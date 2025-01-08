package forms;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.facturacion.Cliente;
import ec.edu.puce.facturacion.Producto;

public class FrmListaClientes extends JInternalFrame {
    private JTable table;
    private DefaultTableModel modeloTabla;
    private List<Cliente> listaClientes;

    public FrmListaClientes() {
        setTitle("Lista de Clientes");
        setClosable(true);
        setBounds(100, 100, 600, 400);

        listaClientes = new ArrayList<>();
        modeloTabla = new DefaultTableModel(
            new String[] { "Cédula", "Nombre", "Apellido", "Dirección", "Teléfono", "Email" }, 0
        );

        table = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnNuevo = new JButton("Nuevo Cliente");
        btnNuevo.addActionListener(e -> nuevoCliente());
        buttonPanel.add(btnNuevo);

        JButton btnEditar = new JButton("Editar Cliente");
        btnEditar.addActionListener(e -> editarCliente());
        buttonPanel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.addActionListener(e -> eliminarCliente());
        buttonPanel.add(btnEliminar);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void nuevoCliente() {
        FrmNuevoCliente dialog = new FrmNuevoCliente(this, null);
        dialog.setVisible(true);
    }

    private void editarCliente() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            Cliente cliente = listaClientes.get(filaSeleccionada);
            FrmNuevoCliente dialog = new FrmNuevoCliente(this, cliente);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para editar");
        }
    }

    private void eliminarCliente() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            listaClientes.remove(filaSeleccionada);
            modeloTabla.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar");
        }
    }

    public void cargarDatosEnTabla(Cliente cliente) {
        listaClientes.add(cliente);
        modeloTabla.addRow(new Object[] {
            cliente.getCedula(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getDireccion(),
            cliente.getTelefono(),
            cliente.getEmail()
        });
    }

    public void actualizarDatosEnTabla(Cliente cliente) {
        int index = listaClientes.indexOf(cliente);
        if (index != -1) {
            listaClientes.set(index, cliente);
            modeloTabla.setValueAt(cliente.getNombre(), index, 1);
            modeloTabla.setValueAt(cliente.getApellido(), index, 2);
            modeloTabla.setValueAt(cliente.getDireccion(), index, 3);
            modeloTabla.setValueAt(cliente.getTelefono(), index, 4);
            modeloTabla.setValueAt(cliente.getEmail(), index, 5);
        }
    }

    public void resetVentanaCliente() {
        table.clearSelection();
    }
    public List<Cliente> getListaClientes() {
        return listaClientes; 
    }

   
    public void setListaClientes(List<Cliente> clientes) {
        this.listaClientes = new ArrayList<>(clientes);
    }
}
