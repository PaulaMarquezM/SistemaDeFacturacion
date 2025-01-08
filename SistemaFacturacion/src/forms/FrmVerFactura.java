package forms;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import ec.edu.puce.facturacion.Factura;

public class FrmVerFactura extends JDialog {
    public FrmVerFactura(Factura factura) {
        setTitle("Ver Factura");
        setModal(true);
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Número:"));
        add(new JLabel(String.valueOf(factura.getNumero()))); // Added missing value

        add(new JLabel("Emisión:"));
        add(new JLabel(factura.getEmision().toString()));

        add(new JLabel("Cliente:"));
        add(new JLabel(factura.getCliente().getNombre()));

        add(new JLabel("Subtotal:"));
        add(new JLabel(String.valueOf(factura.getSubTotal())));

        add(new JLabel("IVA:"));
        add(new JLabel(String.valueOf(factura.getIVA())));

        add(new JLabel("Total:"));
        add(new JLabel(String.valueOf(factura.getTotal())));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);
    }
}