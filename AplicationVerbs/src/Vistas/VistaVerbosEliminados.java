/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Bean.VerboBean;
import Dao.VerboEliminadoDao;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class VistaVerbosEliminados extends javax.swing.JFrame {

    DefaultTableModel tablaVerbos;
    private int idVerbo;

    /**
     * Creates new form VistaVerbosEliminados
     */
    public VistaVerbosEliminados() {
        initComponents();
        setResizable(false);//evitar maximizar
        setLocationRelativeTo(null);//aparezca enmedio la pantalla
        this.consultarVerbosEliminados();
        getContentPane().setBackground(Color.white);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnVaciarPapeleria = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Verbos eliminados");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnVaciarPapeleria.setText("Vaciar papeleria");
        btnVaciarPapeleria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVaciarPapeleriaMouseClicked(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(113, 113, 113))
            .addGroup(layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(btnVaciarPapeleria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVaciarPapeleria)
                    .addComponent(btnRegresar))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVaciarPapeleriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVaciarPapeleriaMouseClicked
        if (VerboEliminadoDao.vaciarPapeleria()) {
            JOptionPane.showMessageDialog(this, "Papeleria de reciclaje vacía");
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrio un error");
        }
        this.consultarVerbosEliminados();
    }//GEN-LAST:event_btnVaciarPapeleriaMouseClicked

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        VistaListaVerbo vistaListaVerbo = new VistaListaVerbo();
        vistaListaVerbo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        evt.getPoint();
        int seleccionado = this.jTable1.rowAtPoint(evt.getPoint());
        this.idVerbo = Integer.parseInt(this.jTable1.getValueAt(seleccionado, 0).toString());

        String[] opciones = {"Eliminar verbo", "Recuperar verbo"};
        String respuesta = (String) JOptionPane.showInputDialog(null, "Seleccione una opción",
                "Seleccione una opción", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);

        if (respuesta.equals("Eliminar verbo")) {

            int dialogButton = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el verbo de la papelera de reciclaje?",
                    "Eliminar verbo", JOptionPane.YES_NO_OPTION);

            if (dialogButton == JOptionPane.YES_OPTION) {
                int[] ids = VerboEliminadoDao.consultarIds(this.idVerbo);
                if (ids != null) {
                    boolean eliminado = VerboEliminadoDao.eliminarVerboPapeleria(ids);
                    if (eliminado) {
                        this.consultarVerbosEliminados();
                        JOptionPane.showMessageDialog(this, "El verbo ha sido eliminado de la papelera de reciclaje");
                    } else {
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error al eliminar el verbo");
                    }
                }
            } else if (dialogButton == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Opcion cancelada");
            }
        } else if (respuesta.equals("Recuperar verbo")) {
            int dialogButton = JOptionPane.showConfirmDialog(null, "¿Desea recuperar el verbo de la papelera de reciclaje?",
                    "Recuperar verbo", JOptionPane.YES_NO_OPTION);

            if (dialogButton == JOptionPane.YES_OPTION) {
                int[] ids = VerboEliminadoDao.consultarIds(this.idVerbo);
                if (ids != null) {
                    
                    boolean recuperado = VerboEliminadoDao.recuperarVerboPapeleria(ids);
                    if (recuperado) {
                        this.consultarVerbosEliminados();
                        JOptionPane.showMessageDialog(this, "El verbo ha sido recuperado de la papelera de reciclaje");
                    } else {
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error al recuperar el verbo");
                    }
                }
            } else if (dialogButton == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Opcion cancelada");
            }
        }


    }//GEN-LAST:event_jTable1MouseClicked

    public void consultarVerbosEliminados() {
        tablaVerbos = new DefaultTableModel(new String[]{"Nº", "Español", "Infinitivo", "Pasado simple",
            "Pasado participio",}, 0);

        List<VerboBean> listaVerbos = VerboEliminadoDao.consultarVerbosEliminados();

        for (VerboBean verbo : listaVerbos) {
            Object[] objeto = {verbo.getIdEspanol(), verbo.getNombreEspanol(), verbo.getNombreInfinitivo(),
                verbo.getNombrePasadoSimple(), verbo.getNombrePasadoParticipio()};
            tablaVerbos.addRow(objeto);
        }

        jTable1.setModel(tablaVerbos);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaVerbosEliminados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaVerbosEliminados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaVerbosEliminados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaVerbosEliminados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaVerbosEliminados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVaciarPapeleria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
