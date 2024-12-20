package forms;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.event.*;

public class FrmMenuPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JDesktopPane desktopPane;
    private boolean listaClientesAbierto = false;

    public FrmMenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setTitle("Sistema de Facturación v1.0.0");
        
        
        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);
        
  
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
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
        mnArchivo.add(mntmClientes);
        
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
}
