package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class FrmMenuPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JDesktopPane desktopPane;
    private boolean listaClientesAbierto = false;
    private boolean listaProductosAbierto = false;
    private boolean listaFacturasAbierto = false;

    public FrmMenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setTitle("Sistema de Facturación v2.0.0");

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnProcesos = new JMenu("Procesos");
        menuBar.add(mnProcesos);

        JMenu mnArchivo = new JMenu("Archivo");
        menuBar.add(mnArchivo);

        JMenuItem mntmClientes = new JMenuItem("Clientes");
        mntmClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!listaClientesAbierto) {
                    FrmListaClientes frmClientes = new FrmListaClientes();
                    desktopPane.add(frmClientes);
                    frmClientes.setVisible(true);
                    listaClientesAbierto = true;

                    frmClientes.addInternalFrameListener(new InternalFrameAdapter() {
                        @Override
                        public void internalFrameClosed(InternalFrameEvent e) {
                            listaClientesAbierto = false;
                        }
                    });
                }
            }
        });
        mnProcesos.add(mntmClientes);

        JMenuItem mntmProductos = new JMenuItem("Productos");
        mntmProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!listaProductosAbierto) {
                    FrmListaProductos frmProductos = new FrmListaProductos();
                    desktopPane.add(frmProductos);
                    frmProductos.setVisible(true);
                    listaProductosAbierto = true;

                    frmProductos.addInternalFrameListener(new InternalFrameAdapter() {
                        @Override
                        public void internalFrameClosed(InternalFrameEvent e) {
                            listaProductosAbierto = false;
                        }
                    });
                }
            }
        });
        mnProcesos.add(mntmProductos);

        JMenuItem mntmFacturas = new JMenuItem("Facturas");
        mntmFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!listaFacturasAbierto) {
                    FrmListaFacturas frmFacturas = new FrmListaFacturas();
                    desktopPane.add(frmFacturas);
                    frmFacturas.setVisible(true);
                    listaFacturasAbierto = true;

                    frmFacturas.addInternalFrameListener(new InternalFrameAdapter() {
                        @Override
                        public void internalFrameClosed(InternalFrameEvent e) {
                            listaFacturasAbierto = false;
                        }
                    });
                }
            }
        });
        mnProcesos.add(mntmFacturas);

        JMenuItem mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(
                        FrmMenuPrincipal.this,
                        "¿Está seguro que desea salir del sistema?",
                        "Confirmar Salida",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmacion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        mnArchivo.add(mntmSalir);
    }

    public static void main(String[] args) {
        FrmMenuPrincipal frame = new FrmMenuPrincipal();
        frame.setVisible(true);
    }
}