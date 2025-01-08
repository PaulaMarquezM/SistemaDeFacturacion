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

<<<<<<< HEAD
        listaClientes = new ArrayList<>();
        modeloTabla = new DefaultTableModel(
            new String[] { "Cédula", "Nombre", "Apellido", "Dirección", "Teléfono", "Email" }, 0
        );
=======
       
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 50, 690, 280);
        getContentPane().add(scrollPane);
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git

        table = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

<<<<<<< HEAD
        JPanel buttonPanel = new JPanel();
        JButton btnNuevo = new JButton("Nuevo Cliente");
        btnNuevo.addActionListener(e -> nuevoCliente());
        buttonPanel.add(btnNuevo);
=======
        
        TableColumn columnaBotones = tblCliente.getColumnModel().getColumn(6);
        columnaBotones.setCellRenderer(new BotonRenderer());
        columnaBotones.setCellEditor(new BotonEditor(new JCheckBox()));
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git

        JButton btnEditar = new JButton("Editar Cliente");
        btnEditar.addActionListener(e -> editarCliente());
        buttonPanel.add(btnEditar);

<<<<<<< HEAD
        JButton btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.addActionListener(e -> eliminarCliente());
        buttonPanel.add(btnEliminar);

        add(buttonPanel, BorderLayout.SOUTH);
=======
      
        JButton btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.setBounds(20, 15, 150, 25);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCliente(null);
            }
        });
        getContentPane().add(btnAgregar);
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
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
<<<<<<< HEAD
        listaClientes.add(cliente);
        modeloTabla.addRow(new Object[] {
=======
        
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (cliente.getCedula().equals(modelo.getValueAt(i, 0))) {
                JOptionPane.showMessageDialog(this,
                    "La cédula ya existe en el sistema.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Object[] fila = {
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
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

<<<<<<< HEAD
   
    public void setListaClientes(List<Cliente> clientes) {
        this.listaClientes = new ArrayList<>(clientes);
=======
    
    class BotonRenderer extends JButton implements TableCellRenderer {
        public BotonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Editar");
            return this;
        }
    }

   
    class BotonEditor extends DefaultCellEditor {
        protected JButton button;
        private boolean isPushed;

        public BotonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = tblCliente.getSelectedRow();
                    if (row != -1) {
                        Cliente clienteSeleccionado = new Cliente(
                            modelo.getValueAt(row, 0).toString(),
                            modelo.getValueAt(row, 1).toString(),
                            modelo.getValueAt(row, 2).toString(),
                            modelo.getValueAt(row, 3).toString(),
                            modelo.getValueAt(row, 4).toString(),
                            modelo.getValueAt(row, 5).toString()
                        );
                        abrirFormularioCliente(clienteSeleccionado);
                    }
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            button.setText("Editar");
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            isPushed = false;
            return "Editar";
        }
>>>>>>> branch 'master' of https://github.com/PaulaMarquezM/SistemaDeFacturacion.git
    }
}
