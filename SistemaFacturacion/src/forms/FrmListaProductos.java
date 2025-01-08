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
import ec.edu.puce.facturacion.Producto;

public class FrmListaProductos extends JInternalFrame {
    private JTable table;
    private DefaultTableModel modeloTabla;
    private List<Producto> listaProductos;
    

    public FrmListaProductos() {
        setTitle("Lista de Productos");
        setClosable(true);
        setBounds(100, 100, 600, 400);

        listaProductos = new ArrayList<>();
        modeloTabla = new DefaultTableModel(
            new String[] { "Nombre", "Código", "Stock", "Precio" }, 0
        );

        table = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnNuevo = new JButton("Nuevo Producto");
        btnNuevo.addActionListener(e -> nuevoProducto());
        buttonPanel.add(btnNuevo);

        JButton btnEditar = new JButton("Editar Producto");
        btnEditar.addActionListener(e -> editarProducto());
        buttonPanel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.addActionListener(e -> eliminarProducto());
        buttonPanel.add(btnEliminar);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void nuevoProducto() {
        FrmNuevoProducto dialog = new FrmNuevoProducto(this, null);
        dialog.setVisible(true);
    }

    private void editarProducto() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            Producto producto = listaProductos.get(filaSeleccionada);
            FrmNuevoProducto dialog = new FrmNuevoProducto(this, producto);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar");
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            listaProductos.remove(filaSeleccionada);
            modeloTabla.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
        }
    }

    public void cargarDatosEnTabla(Producto producto) {
        listaProductos.add(producto);
        modeloTabla.addRow(new Object[] {
            producto.getNombre(),
            producto.getCodigo(),
            producto.getStock(),
            producto.getPrecio(),
        });
    }

    public void actualizarDatosEnTabla(Producto producto) {
        int index = listaProductos.indexOf(producto);
        if (index != -1) {
            listaProductos.set(index, producto);
            modeloTabla.setValueAt(producto.getNombre(), index, 0);
            modeloTabla.setValueAt(producto.getCodigo(), index, 1);
            modeloTabla.setValueAt(producto.getStock(), index, 2);
            modeloTabla.setValueAt(producto.getPrecio(), index, 3);
        }
    }

    public void resetVentanaProducto() {
        table.clearSelection();
    }

    public List<Producto> getListaProductos() {
        return listaProductos; 
    }

   
    public void setListaProductos(List<Producto> productos) {
        this.listaProductos = new ArrayList<>(productos);
    }
    private List<Producto> obtenerListaProductos() {
        FrmListaProductos listaProductosFrame = (FrmListaProductos) getDesktopPane().getComponent(1);
        return listaProductosFrame.getListaProductos();
    }
     
}
