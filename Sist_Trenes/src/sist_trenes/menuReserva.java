/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sist_trenes;


/**
 *
 * @author Usuario
 */
public class menuReserva extends javax.swing.JFrame {

    
    /**
     * Creates new form menuReserva
     */
    public menuReserva() {
        initComponents();
        this.setLocationRelativeTo(null);
        jlblFecha.setText(Sist_Trenes.fechaActual());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelFondo = new javax.swing.JPanel();
        jPnlNavBar = new javax.swing.JPanel();
        jlblTitulo = new javax.swing.JLabel();
        jbtnMenuTarifas = new javax.swing.JButton();
        jPanelPago = new javax.swing.JPanel();
        jbtnDescuento = new javax.swing.JButton();
        jbtnPago = new javax.swing.JButton();
        jPanelOpciones = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jlblPrecio = new javax.swing.JLabel();
        jlblUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtCodigo = new javax.swing.JTextPane();
        jlblFecha = new javax.swing.JLabel();
        jlblDestino = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtxtCodigo1 = new javax.swing.JTextPane();
        jlblNumP = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtxtCdescuento = new javax.swing.JTextPane();
        jlblViaje = new javax.swing.JLabel();
        jlblCodigo = new javax.swing.JLabel();
        jlblNumPasajero1 = new javax.swing.JLabel();
        jbtnMas = new javax.swing.JButton();
        jbtnMenos = new javax.swing.JButton();
        jlblMontoTotal = new javax.swing.JLabel();
        jtxtTotal = new javax.swing.JTextField();
        jDdlDestino = new javax.swing.JComboBox<>();
        jlblFechaHora = new javax.swing.JLabel();
        jlblPrecioUni = new javax.swing.JLabel();
        jPanelMPrecios = new javax.swing.JPanel();
        jlblTituloTarifas = new javax.swing.JLabel();
        jlblRutaCSJ = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelFondo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanelFondo.setLayout(new java.awt.GridBagLayout());

        jPnlNavBar.setBackground(new java.awt.Color(0, 102, 204));
        jPnlNavBar.setToolTipText("");
        jPnlNavBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        jlblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        jlblTitulo.setText("Reserva tus billetes de tren");
        jlblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPnlNavBar.add(jlblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 250, -1));

        jbtnMenuTarifas.setBackground(new java.awt.Color(255, 255, 255));
        jbtnMenuTarifas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnMenuTarifas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/List_26px.png"))); // NOI18N
        jbtnMenuTarifas.setToolTipText("Tarifas de Trenes");
        jbtnMenuTarifas.setFocusable(false);
        jbtnMenuTarifas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMenuTarifasActionPerformed(evt);
            }
        });
        jPnlNavBar.add(jbtnMenuTarifas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

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

        jPanelPago.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPago.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnDescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Loyalty Card_26px.png"))); // NOI18N
        jbtnDescuento.setText("Realizar descuento");
        jbtnDescuento.setFocusable(false);
        jbtnDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDescuentoActionPerformed(evt);
            }
        });
        jPanelPago.add(jbtnDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 180, -1));

        jbtnPago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/colon-costarricense.png"))); // NOI18N
        jbtnPago.setText("Realizar Pago");
        jbtnPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPagoActionPerformed(evt);
            }
        });
        jPanelPago.add(jbtnPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, 170, -1));

        jPanelOpciones.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelOpciones.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jPanelOpciones.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 87, 770, 10));

        jlblPrecio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlblPrecio.setForeground(new java.awt.Color(102, 102, 102));
        jlblPrecio.setText("Precio por Ticket");
        jPanelOpciones.add(jlblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 80, 29));

        jlblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jlblUsuario.setText("Nombre Usuario:");
        jPanelOpciones.add(jlblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 105, -1, 29));

        jScrollPane1.setViewportView(jtxtCodigo);

        jPanelOpciones.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 110, 180, -1));

        jlblFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblFecha.setForeground(new java.awt.Color(0, 0, 0));
        jlblFecha.setText("dd/mm/yyyy");
        jPanelOpciones.add(jlblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, -1, 20));

        jlblDestino.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblDestino.setForeground(new java.awt.Color(0, 0, 0));
        jlblDestino.setText("Destino:");
        jPanelOpciones.add(jlblDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 29));

        jScrollPane3.setViewportView(jtxtCodigo1);

        jPanelOpciones.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 130, -1));

        jlblNumP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNumP.setForeground(new java.awt.Color(0, 0, 0));
        jlblNumP.setText("#");
        jlblNumP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelOpciones.add(jlblNumP, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 20, 20));

        jScrollPane4.setViewportView(jtxtCdescuento);

        jPanelOpciones.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 110, -1));

        jlblViaje.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblViaje.setForeground(new java.awt.Color(0, 0, 0));
        jlblViaje.setText("Código Viajero:");
        jPanelOpciones.add(jlblViaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, -1, 29));

        jlblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblCodigo.setForeground(new java.awt.Color(0, 0, 0));
        jlblCodigo.setText("Código Descuento:");
        jPanelOpciones.add(jlblCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 140, 20));

        jlblNumPasajero1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNumPasajero1.setForeground(new java.awt.Color(0, 0, 0));
        jlblNumPasajero1.setText("Número de Pasajeros:");
        jPanelOpciones.add(jlblNumPasajero1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 160, 20));

        jbtnMas.setBackground(new java.awt.Color(255, 255, 255));
        jbtnMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plus_26px.png"))); // NOI18N
        jbtnMas.setToolTipText("Aumenta Ticket");
        jbtnMas.setBorder(null);
        jbtnMas.setContentAreaFilled(false);
        jbtnMas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnMas.setFocusable(false);
        jbtnMas.setOpaque(true);
        jPanelOpciones.add(jbtnMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 40, 30));

        jbtnMenos.setBackground(new java.awt.Color(255, 255, 255));
        jbtnMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Minus_26px.png"))); // NOI18N
        jbtnMenos.setToolTipText("Disminuye ticket");
        jbtnMenos.setBorder(null);
        jbtnMenos.setBorderPainted(false);
        jbtnMenos.setContentAreaFilled(false);
        jbtnMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnMenos.setFocusable(false);
        jbtnMenos.setOpaque(true);
        jPanelOpciones.add(jbtnMenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 40, 30));

        jlblMontoTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblMontoTotal.setForeground(new java.awt.Color(0, 0, 0));
        jlblMontoTotal.setText("Monto a Paga:");
        jPanelOpciones.add(jlblMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 103, 30));

        jtxtTotal.setEnabled(false);
        jPanelOpciones.add(jtxtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 139, 30));

        jDdlDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jDdlDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDdlDestinoActionPerformed(evt);
            }
        });
        jPanelOpciones.add(jDdlDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 220, -1));

        jlblFechaHora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlblFechaHora.setForeground(new java.awt.Color(102, 102, 102));
        jlblFechaHora.setText("Fecha y hora");
        jPanelOpciones.add(jlblFechaHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 70, 29));

        jlblPrecioUni.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblPrecioUni.setForeground(new java.awt.Color(0, 0, 0));
        jlblPrecioUni.setText("₡");
        jPanelOpciones.add(jlblPrecioUni, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 100, 20));

        jPanelPago.add(jPanelOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 50, 800, 290));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.weightx = 1.9;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(75, 18, 19, 0);
        jPanelFondo.add(jPanelPago, gridBagConstraints);

        jlblTituloTarifas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblTituloTarifas.setForeground(new java.awt.Color(0, 0, 0));
        jlblTituloTarifas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Train_26px.png"))); // NOI18N
        jlblTituloTarifas.setText("Tarifa de Trenes");

        jlblRutaCSJ.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jlblRutaCSJ.setForeground(new java.awt.Color(0, 0, 0));
        jlblRutaCSJ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Train_26px.png"))); // NOI18N
        jlblRutaCSJ.setText("Cartago-San José : ₡1000");

        javax.swing.GroupLayout jPanelMPreciosLayout = new javax.swing.GroupLayout(jPanelMPrecios);
        jPanelMPrecios.setLayout(jPanelMPreciosLayout);
        jPanelMPreciosLayout.setHorizontalGroup(
            jPanelMPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblTituloTarifas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlblRutaCSJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMPreciosLayout.setVerticalGroup(
            jPanelMPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMPreciosLayout.createSequentialGroup()
                .addComponent(jlblTituloTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jlblRutaCSJ, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(425, Short.MAX_VALUE))
        );

        jlblRutaCSJ.getAccessibleContext().setAccessibleDescription("");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 5);
        jPanelFondo.add(jPanelMPrecios, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnMenuTarifasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMenuTarifasActionPerformed
        // TODO add your handling code here:
        int posicion = this.jPanelMPrecios.getX();
        if(posicion > -1)
        {
        Animacion.Animacion.mover_izquierda(0, -185, 2, 2, jPanelMPrecios);
        }
        else
        {
        Animacion.Animacion.mover_derecha(-185, 12, 2, 2, jPanelMPrecios);
        }
    }//GEN-LAST:event_jbtnMenuTarifasActionPerformed

    private void jbtnPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnPagoActionPerformed

    private void jbtnDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnDescuentoActionPerformed

    private void jDdlDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDdlDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDdlDestinoActionPerformed

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
            java.util.logging.Logger.getLogger(menuReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jDdlDestino;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelMPrecios;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelPago;
    private javax.swing.JPanel jPnlNavBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnDescuento;
    private javax.swing.JButton jbtnMas;
    private javax.swing.JButton jbtnMenos;
    private javax.swing.JButton jbtnMenuTarifas;
    private javax.swing.JButton jbtnPago;
    private javax.swing.JLabel jlblCodigo;
    private javax.swing.JLabel jlblDestino;
    private javax.swing.JLabel jlblFecha;
    private javax.swing.JLabel jlblFechaHora;
    private javax.swing.JLabel jlblMontoTotal;
    private javax.swing.JLabel jlblNumP;
    private javax.swing.JLabel jlblNumPasajero1;
    private javax.swing.JLabel jlblPrecio;
    private javax.swing.JLabel jlblPrecioUni;
    private javax.swing.JLabel jlblRutaCSJ;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JLabel jlblTituloTarifas;
    private javax.swing.JLabel jlblUsuario;
    private javax.swing.JLabel jlblViaje;
    private javax.swing.JTextPane jtxtCdescuento;
    private javax.swing.JTextPane jtxtCodigo;
    private javax.swing.JTextPane jtxtCodigo1;
    private javax.swing.JTextField jtxtTotal;
    // End of variables declaration//GEN-END:variables
}
