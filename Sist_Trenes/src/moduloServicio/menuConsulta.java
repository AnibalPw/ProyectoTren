/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloServicio;

import Clases.RegistroTrenes;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.*;
import javax.swing.JOptionPane;
import moduloRegistro.registroTrenes;
import static moduloRegistro.registroTrenes.jtxtIDTren;
import static moduloRegistro.registroTrenes.jtxtCantVagones;
import static moduloRegistro.registroTrenes.jtxtCapacidadTren;


/**
 *
 * @author Usuario
 */
public class menuConsulta extends javax.swing.JFrame {

    String barra = File.separator; //File.separator lo que hace es \\
    String rutaTren_txt = System.getProperty("user.dir")+barra+"txtIndiviual"+barra+"Trenes"+barra;
    
    public menuConsulta() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }
       
    private void MostrarTren()
    {
        File url = new File(rutaTren_txt+jtxtIDTren.getText()+".trenes");
        
        if(jtxtIDTren.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "INDIQUE EL ID QUE DESA BUSCAR");
        }
        else
        {
         if(url.exists())
            {
                try {
                    FileInputStream fileS = new FileInputStream(url);
                    Properties mostrarTrenes = new Properties();
                    mostrarTrenes.load(fileS);
                    
                    jTxtResultados.setText(mostrarTrenes.getProperty("ID"));
                    System.out.println("");
                    jTxtResultados.setText(mostrarTrenes.getProperty("Cantidad Vagones"));
                    System.out.println("");
                    jTxtResultados.setText(mostrarTrenes.getProperty("Capacidad del Tren"));
                    
                } catch (Exception e) {
                }
            }
         else
         {
             JOptionPane.showMessageDialog(rootPane, "Registro inválido");
         }
        }
    }    
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelFondo = new javax.swing.JPanel();
        jPnlNavBar = new javax.swing.JPanel();
        jlblTituloConsulta = new javax.swing.JLabel();
        jbtnMenuDesplegable = new javax.swing.JButton();
        jPnlConsulta = new javax.swing.JPanel();
        jPanelOpciones = new javax.swing.JPanel();
        jTxtResultados = new javax.swing.JTextField();
        jlblViaje = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtxtIDClienteC = new javax.swing.JTextPane();
        jlblUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtIDEstacionC = new javax.swing.JTextPane();
        jlblCodigo = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtxtIDTrenC = new javax.swing.JTextPane();
        jbtnBuscarDatos = new javax.swing.JButton();
        jPnlMenuConsulta = new javax.swing.JPanel();
        jBtnHome = new javax.swing.JButton();
        jBtnReportes = new javax.swing.JButton();
        jBtnRegistro = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jBtnRecorrido = new javax.swing.JButton();
        jBtnReservaEspacio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelFondo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanelFondo.setLayout(new java.awt.GridBagLayout());

        jPnlNavBar.setBackground(new java.awt.Color(0, 102, 204));
        jPnlNavBar.setToolTipText("");
        jPnlNavBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTituloConsulta.setBackground(new java.awt.Color(0, 0, 0));
        jlblTituloConsulta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblTituloConsulta.setForeground(new java.awt.Color(0, 0, 0));
        jlblTituloConsulta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPnlNavBar.add(jlblTituloConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 270, 30));

        jbtnMenuDesplegable.setBackground(new java.awt.Color(255, 255, 255));
        jbtnMenuDesplegable.setForeground(new java.awt.Color(255, 255, 255));
        jbtnMenuDesplegable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/List_26px.png"))); // NOI18N
        jbtnMenuDesplegable.setToolTipText("Tarifas de Trenes");
        jbtnMenuDesplegable.setFocusable(false);
        jbtnMenuDesplegable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMenuDesplegableActionPerformed(evt);
            }
        });
        jPnlNavBar.add(jbtnMenuDesplegable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 343;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 8, 3, 5);
        jPanelFondo.add(jPnlNavBar, gridBagConstraints);

        jPnlConsulta.setBackground(new java.awt.Color(255, 255, 255));
        jPnlConsulta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelOpciones.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelOpciones.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelOpciones.add(jTxtResultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 200));

        jPnlConsulta.add(jPanelOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 570, 200));

        jlblViaje.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblViaje.setForeground(new java.awt.Color(0, 0, 0));
        jlblViaje.setText("Consulta por Cliente: ");
        jPnlConsulta.add(jlblViaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 29));

        jScrollPane3.setViewportView(jtxtIDClienteC);

        jPnlConsulta.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 160, -1));

        jlblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jlblUsuario.setText("Consulta por Estación: ");
        jPnlConsulta.add(jlblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, 29));

        jScrollPane1.setViewportView(jtxtIDEstacionC);

        jPnlConsulta.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 130, -1));

        jlblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblCodigo.setForeground(new java.awt.Color(0, 0, 0));
        jlblCodigo.setText("Consulta por Tren:");
        jPnlConsulta.add(jlblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 140, 20));

        jScrollPane4.setViewportView(jtxtIDTrenC);

        jPnlConsulta.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 110, -1));

        jbtnBuscarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Search_26px.png"))); // NOI18N
        jbtnBuscarDatos.setText("Buscar");
        jbtnBuscarDatos.setFocusable(false);
        jbtnBuscarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarDatosActionPerformed(evt);
            }
        });
        jPnlConsulta.add(jbtnBuscarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 110, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.weightx = 1.9;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(75, 18, 19, 0);
        jPanelFondo.add(jPnlConsulta, gridBagConstraints);

        jBtnHome.setBackground(new java.awt.Color(204, 204, 204));
        jBtnHome.setForeground(new java.awt.Color(91, 91, 91));
        jBtnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dog House_26px.png"))); // NOI18N
        jBtnHome.setText("INICIO");
        jBtnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnHome.setFocusable(false);
        jBtnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHomeActionPerformed(evt);
            }
        });

        jBtnReportes.setBackground(new java.awt.Color(204, 204, 204));
        jBtnReportes.setForeground(new java.awt.Color(91, 91, 91));
        jBtnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/System Report_26px.png"))); // NOI18N
        jBtnReportes.setText("REPORTES");
        jBtnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnReportes.setFocusable(false);

        jBtnRegistro.setBackground(new java.awt.Color(204, 204, 204));
        jBtnRegistro.setForeground(new java.awt.Color(91, 91, 91));
        jBtnRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Document_26px.png"))); // NOI18N
        jBtnRegistro.setText("REGISTRAR");
        jBtnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegistro.setFocusable(false);
        jBtnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistroActionPerformed(evt);
            }
        });

        jBtnRecorrido.setBackground(new java.awt.Color(204, 204, 204));
        jBtnRecorrido.setForeground(new java.awt.Color(91, 91, 91));
        jBtnRecorrido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Place_Marker_26px_1.png"))); // NOI18N
        jBtnRecorrido.setText("RECORRIDOS");
        jBtnRecorrido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRecorrido.setFocusable(false);
        jBtnRecorrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRecorridoActionPerformed(evt);
            }
        });

        jBtnReservaEspacio.setBackground(new java.awt.Color(204, 204, 204));
        jBtnReservaEspacio.setForeground(new java.awt.Color(91, 91, 91));
        jBtnReservaEspacio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Train Ticket_26px.png"))); // NOI18N
        jBtnReservaEspacio.setText("RESERVA ESPACIO");
        jBtnReservaEspacio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnReservaEspacio.setFocusable(false);
        jBtnReservaEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReservaEspacioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlMenuConsultaLayout = new javax.swing.GroupLayout(jPnlMenuConsulta);
        jPnlMenuConsulta.setLayout(jPnlMenuConsultaLayout);
        jPnlMenuConsultaLayout.setHorizontalGroup(
            jPnlMenuConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnReportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addComponent(jBtnRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPnlMenuConsultaLayout.createSequentialGroup()
                .addComponent(jBtnReservaEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPnlMenuConsultaLayout.setVerticalGroup(
            jPnlMenuConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlMenuConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnReservaEspacio)
                .addGap(18, 18, 18)
                .addComponent(jBtnRegistro)
                .addGap(18, 18, 18)
                .addComponent(jBtnRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 5);
        jPanelFondo.add(jPnlMenuConsulta, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnMenuDesplegableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMenuDesplegableActionPerformed
        // TODO add your handling code here:
        int posicion = this.jPnlMenuConsulta.getX();
        if(posicion > -1)
        {
        Animacion.Animacion.mover_izquierda(0, -185, 2, 2, jPnlMenuConsulta);
        }
        else
        {
        Animacion.Animacion.mover_derecha(-185, 12, 2, 2, jPnlMenuConsulta);
        }
    }//GEN-LAST:event_jbtnMenuDesplegableActionPerformed

    private void jBtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistroActionPerformed
    
        moduloRegistro.menuRegistro Registro = new  moduloRegistro.menuRegistro();
        Registro.setVisible(true);
        this.setVisible(false);
        moduloRegistro.menuRegistro.jlblTituloRegistro.setText("Registro de Funcionarios");
        
    }//GEN-LAST:event_jBtnRegistroActionPerformed

    private void jBtnReservaEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReservaEspacioActionPerformed
        moduloServicio.menuReserva Reserva = new  moduloServicio.menuReserva();
        Reserva.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jBtnReservaEspacioActionPerformed

    private void jBtnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHomeActionPerformed
        sist_trenes.IntMenu Inicio = new sist_trenes.IntMenu();
        Inicio.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jBtnHomeActionPerformed

    private void jBtnRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRecorridoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnRecorridoActionPerformed

    private void jbtnBuscarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarDatosActionPerformed
       //MostrarTren();
       moduloRegistro.registroTrenes m = new moduloRegistro.registroTrenes();
       m.MostrarTren();
    }//GEN-LAST:event_jbtnBuscarDatosActionPerformed

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
            java.util.logging.Logger.getLogger(menuConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnHome;
    private javax.swing.JButton jBtnRecorrido;
    private javax.swing.JButton jBtnRegistro;
    private javax.swing.JButton jBtnReportes;
    private javax.swing.JButton jBtnReservaEspacio;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPnlConsulta;
    private javax.swing.JPanel jPnlMenuConsulta;
    private javax.swing.JPanel jPnlNavBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JTextField jTxtResultados;
    private javax.swing.JButton jbtnBuscarDatos;
    private javax.swing.JButton jbtnMenuDesplegable;
    private javax.swing.JLabel jlblCodigo;
    public static javax.swing.JLabel jlblTituloConsulta;
    private javax.swing.JLabel jlblUsuario;
    private javax.swing.JLabel jlblViaje;
    private javax.swing.JTextPane jtxtIDClienteC;
    private javax.swing.JTextPane jtxtIDEstacionC;
    public static javax.swing.JTextPane jtxtIDTrenC;
    // End of variables declaration//GEN-END:variables
}
