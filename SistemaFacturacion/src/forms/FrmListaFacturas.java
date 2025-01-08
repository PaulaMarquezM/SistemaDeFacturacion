package forms;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ec.edu.puce.facturacion.Factura;

public class FrmListaFacturas extends JInternalFrame implements FrmNuevaFactura.FacturaListener {
    private JTable table;
    private DefaultTableModel modeloTabla;
    private List<Factura> listaFacturas;

    public FrmListaFacturas() {
        setTitle("Lista de Facturas");
        setClosable(true);
        setBounds(100, 100, 600, 400);

        listaFacturas = new ArrayList<>();
        modeloTabla = new DefaultTableModel(
            new String[] { "Número", "Emisión", "Cliente", "Subtotal", "IVA", "Total" }, 0
        );

        table = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnNuevo = new JButton("Nueva factura");
        btnNuevo.addActionListener(e -> nuevaFactura());
        buttonPanel.add(btnNuevo);

        JButton btnEditar = new JButton("Ver factura");
        btnEditar.addActionListener(e -> editarFactura());
        buttonPanel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar factura");
        btnEliminar.addActionListener(e -> eliminarFactura());
        buttonPanel.add(btnEliminar);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void nuevaFactura() {
        FrmNuevaFactura dialog = new FrmNuevaFactura(null);
        dialog.setFacturarListener(this); 
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(dialog);
        dialog.setSize(800, 600);
        dialog.setVisible(true);
    }

    @Override
    public void onFacturaGenerada(Factura factura) {
        cargarDatosEnTabla(factura); 
    }

    private void editarFactura() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            Factura factura = listaFacturas.get(filaSeleccionada);
            FrmVerFactura dialog = new FrmVerFactura(factura);
            dialog.setVisible(true); 
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una factura para ver.");
        }
    }

    private void eliminarFactura() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            listaFacturas.remove(filaSeleccionada);
            modeloTabla.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione para eliminar");
        }
    }

    public void cargarDatosEnTabla(Factura factura) {
        listaFacturas.add(factura);
        modeloTabla.addRow(new Object[] {
            factura.getNumero(),
            factura.getEmision(),
            factura.getCliente().getNombre(), 
            factura.getSubTotal(),
            factura.getIVA(),
            factura.getTotal()
        });
    }

    public void actualizarDatosEnTabla(Factura factura) {
        int index = listaFacturas.indexOf(factura);
        if (index != -1) {
            listaFacturas.set(index, factura);
            modeloTabla.setValueAt(factura.getNumero(), index, 0);
            modeloTabla.setValueAt(factura.getEmision(), index, 1);
            modeloTabla.setValueAt(factura.getCliente().getNombre(), index, 2);
            modeloTabla.setValueAt(factura.getSubTotal(), index, 3);
            modeloTabla.setValueAt(factura.getIVA(), index, 4);
            modeloTabla.setValueAt(factura.getTotal(), index, 5);
        }
    }
}

