package forms;

import javax.swing.*;
import javax.swing.table.*;

import ec.edu.puce.facturacion.Cliente;

import java.awt.*;
import java.awt.event.*;


public class FrmListaClientes extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private JTable tblCliente;
    private DefaultTableModel modelo;
    private boolean nuevoClienteAbierto = false;

    public FrmListaClientes() {
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setTitle("Lista de Clientes");
        setBounds(100, 100, 750, 400);
        getContentPane().setLayout(null);

       
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 50, 690, 280);
        getContentPane().add(scrollPane);

        tblCliente = new JTable();
        modelo = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Cédula", "Nombre", "Apellido", "Dirección", "Teléfono", "Email", "Acciones"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        tblCliente.setModel(modelo);

        
        TableColumn columnaBotones = tblCliente.getColumnModel().getColumn(6);
        columnaBotones.setCellRenderer(new BotonRenderer());
        columnaBotones.setCellEditor(new BotonEditor(new JCheckBox()));

        scrollPane.setViewportView(tblCliente);

      
        JButton btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.setBounds(20, 15, 150, 25);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCliente(null);
            }
        });
        getContentPane().add(btnAgregar);
    }

    public void abrirFormularioCliente(Cliente cliente) {
        if (!nuevoClienteAbierto) {
            FrmNuevoCliente dialog = new FrmNuevoCliente(this, cliente);
            dialog.setModal(true);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            nuevoClienteAbierto = true;
        } else {
            JOptionPane.showMessageDialog(this, 
                "Ya hay una ventana de cliente abierta",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public void cargarDatosEnTabla(Cliente cliente) {
        
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
            cliente.getCedula(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getDireccion(),
            cliente.getTelefono(),
            cliente.getEmail(),
            "Editar"
        };
        modelo.addRow(fila);
    }

    public void actualizarDatosEnTabla(Cliente cliente) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (cliente.getCedula().equals(modelo.getValueAt(i, 0))) {
                modelo.setValueAt(cliente.getNombre(), i, 1);
                modelo.setValueAt(cliente.getApellido(), i, 2);
                modelo.setValueAt(cliente.getDireccion(), i, 3);
                modelo.setValueAt(cliente.getTelefono(), i, 4);
                modelo.setValueAt(cliente.getEmail(), i, 5);
                break;
            }
        }
    }

    public void resetVentanaCliente() {
        nuevoClienteAbierto = false;
    }

    
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
    }
}
