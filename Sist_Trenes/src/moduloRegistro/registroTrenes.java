/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moduloRegistro;
import Clases.Limpiar_txt;
import Clases.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static moduloServicio.menuConsulta.jtxtIDTrenC;
import static moduloServicio.menuConsulta.jTextAreaResultado;

/**
 *
 * @author Usuario
 */
public class registroTrenes extends javax.swing.JFrame {

     Limpiar_txt lt = new Limpiar_txt();
    
    private String ruta_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\BDtxt\\RegistroTrenes.txt";
    private String rutaIndividual_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\txtIndiviual\\Trenes\\Trenes";
    String barra = File.separator; //File.separator lo que hace es \\
    String rutaTren_txt = System.getProperty("user.dir")+barra+"txtIndiviual"+barra+"Trenes"+barra;
    
    
    RegistroTrenes RegTrenes;
    ProcRTrenes PRTrenes;
    
        int clic_tabla;
        
    public registroTrenes() {
        initComponents();
        this.setLocationRelativeTo(null);
        
          PRTrenes = new ProcRTrenes();
        
        try {
            cargarTrenes_txt();
            listarRegistro();
        } catch (Exception e) {
            mensaje("No existe el archivo txt");
        } 
    }
        
    
      public void AgregarTrenRegistro(File ruta){
        try{
            if(IDTren() == -666)mensaje("Ingresar ID en número entero");
            else if(InVagon() == -666)mensaje("Ingresar Cantidad de Vagones");
            else if(InCapa() == -666)mensaje("Ingresar Capacidad del Tren");
            else{
                RegTrenes = new RegistroTrenes(IDTren(), InVagon(), InCapa());
                if(PRTrenes.BuscarID(RegTrenes.getIDTrenes())!= -1)mensaje("Este ID ya existe");
                else PRTrenes.AgregarTren(RegTrenes);
                CrearTXTTrenesIN();
                
                GuardarTren_txt();
                listarRegistro();
                lt.limpiar_texto(jPnlInfoRegRecorridos); 
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
      
      
      public void cargarTrenes_txt(){
        File ruta = new File(ruta_txt);
        try{
            
            FileReader ficheroSalida = new FileReader(ruta);
            BufferedReader BR = new BufferedReader(ficheroSalida);
            
            String linea = null;
             while((linea = BR.readLine())!= null){
                StringTokenizer St = new StringTokenizer(linea, ",");
                RegTrenes = new RegistroTrenes();
                RegTrenes.setIDTrenes(Integer.parseInt(St.nextToken()));
                RegTrenes.setVagonesTren(Integer.valueOf(St.nextToken()));
                RegTrenes.setCapacidadTren(Integer.parseInt(St.nextToken()));
                PRTrenes.AgregarTren(RegTrenes);
            }
            BR.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
      
      
      public void GuardarTren_txt(){
          
        try{
            BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(ruta_txt)));
            for(int i = 0; i < PRTrenes.cantidadTren(); i++){
                RegTrenes = PRTrenes.ObtenerTren(i);

                 ficheroSalida.write(String.valueOf(RegTrenes.getIDTrenes()+"," +RegTrenes.getVagonesTren()+"," +RegTrenes.getCapacidadTren()));
                 ficheroSalida.newLine();
            }
             ficheroSalida.close();
            
        }catch(Exception ex){
            mensaje("Error al guardar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
      
       public void modificarRegistroTrenes(File ruta){
        try{
            
            File dir = new File(ruta_txt);
            File url = new File(rutaIndividual_txt + jtxtIDTren.getText()+".trenes");
            
            if(jtxtIDTren.getText().equals(""))
            {
               JOptionPane.showMessageDialog(rootPane, "INIDIQUE EL REGISTRO");
            }
            else
            {
                if(dir.exists() && url.exists())
                {
                if(IDTren() == -666)mensaje("Ingresar ID en número entero");
                else if(InVagon() == -666)mensaje("Ingresar Cantidad de Vagones");
                else if(InCapa() == -666)mensaje("Ingresar Capacidad del Tren");
                else
                {
                    int ID = PRTrenes.BuscarID(IDTren());
                    RegTrenes = new RegistroTrenes(IDTren(), InVagon(), InCapa());

                    if(ID == -1)PRTrenes.AgregarTren(RegTrenes);
                    else PRTrenes.ModificarTren(ID, RegTrenes);
                    EditarTXTTrenesIN();
                    
                    GuardarTren_txt();
                    listarRegistro();
                    lt.limpiar_texto(jPnlInfoRegRecorridos);
                }
                }
                else
                {
                 JOptionPane.showMessageDialog(rootPane, "NO EXISTE ESTE REGISTRO");
                }
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
       
       
    public void eliminarRegistroTrenes()
    {
        try{
            if(IDTren() == -666) mensaje("Ingrese ID entero");
            
            else{
                int ID = PRTrenes.BuscarID(IDTren());
                if(ID == -1) mensaje("Número de ID no existe");
                
                else{
                    int si = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este la Estación","Si / No",0);
                    if(si == 0){
                        PRTrenes.EliminarTren(ID);
                        EliminarTXTTrenesIN();
                        
                        GuardarTren_txt();
                        listarRegistro();
                        lt.limpiar_texto(jPnlInfoRegRecorridos);
                    }
                }
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
       public void listarRegistro(){
        DefaultTableModel dt = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
         
        dt.addColumn("ID");
        dt.addColumn("Cantidad Vagones");
        dt.addColumn("Capacidad Tren");
    
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < PRTrenes.cantidadTren(); i++){
            RegTrenes = PRTrenes.ObtenerTren(i);
            fila[0] = RegTrenes.getIDTrenes();
            fila[1] = RegTrenes.getVagonesTren();
            fila[2] = RegTrenes.getCapacidadTren();
          
            dt.addRow(fila);
        }
        jTableDatosTren.setModel(dt);
        jTableDatosTren.setRowHeight(30);
    }
        
       
       public int IDTren(){
        try{
            int IDT = Integer.parseInt(jtxtIDTren.getText().trim());
            return IDT;
        }catch(Exception ex){
            return -666;
        }
    }    
       public int InVagon(){
        try{
            int CanVagones = Integer.parseInt(jtxtCantVagones.getText().trim());
            return CanVagones;
        }catch(Exception ex){
            return -666;
        }
    }
             public int InCapa(){
        try{
            int  CapaTren= Integer.parseInt(jtxtCapacidadTren.getText().trim());
            return CapaTren;
        }catch(Exception ex){
            return -666;
        }
    }
       
          public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
  
    
          private void CrearTXTTrenesIN()
       {
           String archivo = jtxtIDTren.getText()+".trenes";
           //File crear_ubicacion = new File(rutaIndividual_txt);
           File crear_archivo = new File(rutaIndividual_txt + archivo);
           
           if(jtxtIDTren.getText().equals(""))
           {
            JOptionPane.showMessageDialog(rootPane, "NO HAY ID");
           }
           else
           {
               try 
               {
                    if(crear_archivo.exists())
                    {
                        
                    }
                    else
                    {
                        Formatter crear = new Formatter(rutaIndividual_txt + archivo);
                        crear.format("%s\r\n%s\r\n%s", 
                                "ID="+jtxtIDTren.getText(),
                                "CantidadVagones="+jtxtCantVagones.getText(), 
                                "CapacidaddelTren="+jtxtCapacidadTren.getText());
                                
                        crear.close();
                    }
               } 
               catch (Exception e)
               {
                   
               }
           } 
       }
       
       private void EditarTXTTrenesIN()
       {
           File url = new File(rutaIndividual_txt + jtxtIDTren.getText()+".trenes");
           if(jtxtIDTren.getText().equals(""))
           {
               JOptionPane.showMessageDialog(rootPane, "INIDIQUE EL REGISTRO");
           }
           else
           {
               if(url.exists())
               {
                   try 
                    {
                     FileWriter escrito = new FileWriter(rutaIndividual_txt + jtxtIDTren.getText()+".trenes");
                     String ID = "ID=";
                     String Cantidad = "Cantidad Vagones=";
                     String Capacidad = "Capacidad del Tren=";
                     
                        PrintWriter guardar = new PrintWriter(escrito);
                        guardar.println(ID+jtxtIDTren.getText());
                        guardar.println(Cantidad+jtxtCantVagones.getText());
                        guardar.println(Capacidad+jtxtCapacidadTren.getText());
                        
                        escrito.close();
                    } 
                    catch (Exception e) {
                    }
               }
               else
               {
                   JOptionPane.showMessageDialog(rootPane, "NO EXISTE ESTE REGISTRO");
               }
           }
       }
       
       private void EliminarTXTTrenesIN()
       {
            File url = new File(rutaIndividual_txt + jtxtIDTren.getText()+".trenes");
            if(jtxtIDTren.getText().equals(""))
            {
             JOptionPane.showMessageDialog(rootPane, "NO HAY ID");
            }
            else
            {
                if(url.exists())
                {
                    try {
                        FileInputStream cerrar = new FileInputStream(url);
                        cerrar.close();
                        System.gc();
                        url.delete();
                        
                    } catch (Exception e) {
                    }
                }
            }
       }
          
       
       public void MostrarTren()
    {
        File url = new File(rutaTren_txt+jtxtIDTrenC.getText()+".trenes");
        
        if(jtxtIDTrenC.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "INDIQUE EL ID QUE DESEA BUSCAR");
        }
        else
        {
         if(url.exists())
            {
                try {
//                    FileInputStream fileS = new FileInputStream(url);
//                    Properties mostrarTrenes = new Properties();
//                    mostrarTrenes.load(fileS);
                    
                   BufferedReader txt = new BufferedReader( new FileReader(url));
                   String linea = txt.readLine();
                   
                   while(linea != null)
                   {
                       jTextAreaResultado.append(linea + "\r\n");
                       linea = txt.readLine();
                   }
                   
                   // jTxtResultados.setText(mostrarTrenes.getProperty("ID"));
                    //System.out.println("");
                    //jTxtResultados.setText(mostrarTrenes.getProperty("Cantidad Vagones"));
                    //System.out.println("");
                    //jTxtResultados.setText(mostrarTrenes.getProperty("CapacidaddelTren"));
                    
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
        jlblTituloRegistroTrenes = new javax.swing.JLabel();
        jbtnMenuDesplegable = new javax.swing.JButton();
        jPnlInfoRegRecorridos = new javax.swing.JPanel();
        jPanelOpciones = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableDatosTren = new javax.swing.JTable();
        jlblIDTren = new javax.swing.JLabel();
        jlblCantVagones = new javax.swing.JLabel();
        jbtnGuardarDatosTren = new javax.swing.JButton();
        jbtnModificarDatosTren = new javax.swing.JButton();
        jbtnEliminarDatosTren = new javax.swing.JButton();
        jbtnLimpiarTren = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtxtCantVagones = new javax.swing.JTextPane();
        jlblCapaTren = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtxtCapacidadTren = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtxtIDTren = new javax.swing.JTextPane();
        jPnlMenuRecorrido = new javax.swing.JPanel();
        jBtnHome = new javax.swing.JButton();
        jBtnReportes = new javax.swing.JButton();
        jBtnReservarEspacio = new javax.swing.JButton();
        jBtnConsulta = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jBtnRegRecorrido = new javax.swing.JButton();
        jBtnRegFuncionario = new javax.swing.JButton();
        jBtnRecorrido = new javax.swing.JButton();
        jBtnRegTrenes = new javax.swing.JButton();
        jBtnRegEstaciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelFondo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanelFondo.setLayout(new java.awt.GridBagLayout());

        jPnlNavBar.setBackground(new java.awt.Color(0, 102, 204));
        jPnlNavBar.setToolTipText("");
        jPnlNavBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTituloRegistroTrenes.setBackground(new java.awt.Color(0, 0, 0));
        jlblTituloRegistroTrenes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblTituloRegistroTrenes.setForeground(new java.awt.Color(0, 0, 0));
        jlblTituloRegistroTrenes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPnlNavBar.add(jlblTituloRegistroTrenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 240, 30));

        jbtnMenuDesplegable.setBackground(new java.awt.Color(255, 255, 255));
        jbtnMenuDesplegable.setForeground(new java.awt.Color(255, 255, 255));
        jbtnMenuDesplegable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/List_26px.png"))); // NOI18N
        jbtnMenuDesplegable.setToolTipText("Menú Registro");
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

        jPnlInfoRegRecorridos.setBackground(new java.awt.Color(255, 255, 255));
        jPnlInfoRegRecorridos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelOpciones.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelOpciones.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableDatosTren.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Tren", "Cantidad Vagones", "Capacidad Tren"
            }
        ));
        jTableDatosTren.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDatosTrenMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableDatosTren);

        jPanelOpciones.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 220));

        jPnlInfoRegRecorridos.add(jPanelOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 800, 220));

        jlblIDTren.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblIDTren.setForeground(new java.awt.Color(0, 0, 0));
        jlblIDTren.setText("ID de tren:");
        jPnlInfoRegRecorridos.add(jlblIDTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 29));

        jlblCantVagones.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblCantVagones.setForeground(new java.awt.Color(0, 0, 0));
        jlblCantVagones.setText("Vagones del tren:");
        jPnlInfoRegRecorridos.add(jlblCantVagones, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 130, 20));

        jbtnGuardarDatosTren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Save Archive_26px.png"))); // NOI18N
        jbtnGuardarDatosTren.setText("Guardar");
        jbtnGuardarDatosTren.setFocusable(false);
        jbtnGuardarDatosTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarDatosTrenActionPerformed(evt);
            }
        });
        jPnlInfoRegRecorridos.add(jbtnGuardarDatosTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 130, -1));

        jbtnModificarDatosTren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Edit File_26px.png"))); // NOI18N
        jbtnModificarDatosTren.setText("Modificar");
        jbtnModificarDatosTren.setFocusable(false);
        jbtnModificarDatosTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarDatosTrenActionPerformed(evt);
            }
        });
        jPnlInfoRegRecorridos.add(jbtnModificarDatosTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 130, -1));

        jbtnEliminarDatosTren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Trash_26px.png"))); // NOI18N
        jbtnEliminarDatosTren.setText("Eliminar");
        jbtnEliminarDatosTren.setFocusable(false);
        jbtnEliminarDatosTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarDatosTrenActionPerformed(evt);
            }
        });
        jPnlInfoRegRecorridos.add(jbtnEliminarDatosTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 120, -1));

        jbtnLimpiarTren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Disposal_26px.png"))); // NOI18N
        jbtnLimpiarTren.setToolTipText("Limpiar información");
        jbtnLimpiarTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLimpiarTrenActionPerformed(evt);
            }
        });
        jPnlInfoRegRecorridos.add(jbtnLimpiarTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 40, -1));

        jScrollPane6.setViewportView(jtxtCantVagones);

        jPnlInfoRegRecorridos.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 250, -1));

        jlblCapaTren.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblCapaTren.setForeground(new java.awt.Color(0, 0, 0));
        jlblCapaTren.setText("Capacidad del tren:");
        jPnlInfoRegRecorridos.add(jlblCapaTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 140, 20));

        jScrollPane7.setViewportView(jtxtCapacidadTren);

        jPnlInfoRegRecorridos.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 250, -1));

        jScrollPane8.setViewportView(jtxtIDTren);

        jPnlInfoRegRecorridos.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 250, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.weightx = 1.9;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(75, 18, 19, 0);
        jPanelFondo.add(jPnlInfoRegRecorridos, gridBagConstraints);

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

        jBtnReservarEspacio.setBackground(new java.awt.Color(204, 204, 204));
        jBtnReservarEspacio.setForeground(new java.awt.Color(91, 91, 91));
        jBtnReservarEspacio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Train Ticket_26px.png"))); // NOI18N
        jBtnReservarEspacio.setText("Reserva Espacio");
        jBtnReservarEspacio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnReservarEspacio.setFocusable(false);
        jBtnReservarEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReservarEspacioActionPerformed(evt);
            }
        });

        jBtnConsulta.setBackground(new java.awt.Color(204, 204, 204));
        jBtnConsulta.setForeground(new java.awt.Color(91, 91, 91));
        jBtnConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Questions_26px.png"))); // NOI18N
        jBtnConsulta.setText("CONSULTAS");
        jBtnConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnConsulta.setFocusable(false);
        jBtnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConsultaActionPerformed(evt);
            }
        });

        jBtnRegRecorrido.setBackground(new java.awt.Color(204, 204, 204));
        jBtnRegRecorrido.setForeground(new java.awt.Color(91, 91, 91));
        jBtnRegRecorrido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Tracks_26px.png"))); // NOI18N
        jBtnRegRecorrido.setText("Registrar Recorrido");
        jBtnRegRecorrido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegRecorrido.setFocusable(false);
        jBtnRegRecorrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegRecorridoActionPerformed(evt);
            }
        });

        jBtnRegFuncionario.setBackground(new java.awt.Color(204, 204, 204));
        jBtnRegFuncionario.setForeground(new java.awt.Color(91, 91, 91));
        jBtnRegFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Collaborator Male_26px.png"))); // NOI18N
        jBtnRegFuncionario.setText("Registro de Maquinistas");
        jBtnRegFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegFuncionario.setFocusable(false);
        jBtnRegFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegFuncionarioActionPerformed(evt);
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

        jBtnRegTrenes.setBackground(new java.awt.Color(204, 204, 204));
        jBtnRegTrenes.setForeground(new java.awt.Color(91, 91, 91));
        jBtnRegTrenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Train_26px.png"))); // NOI18N
        jBtnRegTrenes.setText("Registrar Trenes");
        jBtnRegTrenes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegTrenes.setFocusable(false);
        jBtnRegTrenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegTrenesActionPerformed(evt);
            }
        });

        jBtnRegEstaciones.setBackground(new java.awt.Color(204, 204, 204));
        jBtnRegEstaciones.setForeground(new java.awt.Color(91, 91, 91));
        jBtnRegEstaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Railway Station_26px.png"))); // NOI18N
        jBtnRegEstaciones.setText("Registrar Estaciones");
        jBtnRegEstaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegEstaciones.setFocusable(false);
        jBtnRegEstaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegEstacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlMenuRecorridoLayout = new javax.swing.GroupLayout(jPnlMenuRecorrido);
        jPnlMenuRecorrido.setLayout(jPnlMenuRecorridoLayout);
        jPnlMenuRecorridoLayout.setHorizontalGroup(
            jPnlMenuRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnReportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnReservarEspacio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addComponent(jBtnRegRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRegFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRegTrenes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRegEstaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnlMenuRecorridoLayout.setVerticalGroup(
            jPnlMenuRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlMenuRecorridoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnReservarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jBtnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnRegFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRegTrenes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRegRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRegEstaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 5);
        jPanelFondo.add(jPnlMenuRecorrido, gridBagConstraints);

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
        int posicion = this.jPnlMenuRecorrido.getX();
        if(posicion > -1)
        {
        Animacion.Animacion.mover_izquierda(0, -210, 2, 2, jPnlMenuRecorrido);
        }
        else
        {
        Animacion.Animacion.mover_derecha(-210, 12, 2, 2, jPnlMenuRecorrido);
        }
    }//GEN-LAST:event_jbtnMenuDesplegableActionPerformed

    private void jBtnReservarEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReservarEspacioActionPerformed
        moduloServicio.menuReserva Reserva = new  moduloServicio.menuReserva();
        Reserva.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jBtnReservarEspacioActionPerformed

    private void jBtnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConsultaActionPerformed
        moduloServicio.menuConsulta Consulta = new  moduloServicio.menuConsulta();
        Consulta.setVisible(true);
        this.setVisible(false);
        moduloServicio.menuConsulta.jlblTituloConsulta.setText("Consulta Información");
    }//GEN-LAST:event_jBtnConsultaActionPerformed

    private void jBtnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHomeActionPerformed
        sist_trenes.IntMenu Inicio = new sist_trenes.IntMenu();
        Inicio.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jBtnHomeActionPerformed

    private void jBtnRegRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegRecorridoActionPerformed
        registroRecorridos regRecorridos = new registroRecorridos();
        regRecorridos.setVisible(true);
        this.setVisible(false);
        moduloRegistro.registroRecorridos.jlblTituloRegistroTrenes.setText("Registro de Recorridos");
    }//GEN-LAST:event_jBtnRegRecorridoActionPerformed

    private void jBtnRegFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegFuncionarioActionPerformed
        menuRegistro RegistroFunc = new menuRegistro();
        RegistroFunc.setVisible(true);
        this.setVisible(false);
        moduloRegistro.menuRegistro.jlblTituloRegistro.setText("Registro de Funcionarios");
    }//GEN-LAST:event_jBtnRegFuncionarioActionPerformed

    private void jbtnEliminarDatosTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarDatosTrenActionPerformed
        this.eliminarRegistroTrenes();
    }//GEN-LAST:event_jbtnEliminarDatosTrenActionPerformed

    private void jbtnModificarDatosTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarDatosTrenActionPerformed
        File ruta = new File(jtxtIDTren.getText());
        this.modificarRegistroTrenes(ruta);
    }//GEN-LAST:event_jbtnModificarDatosTrenActionPerformed

    private void jbtnGuardarDatosTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarDatosTrenActionPerformed
       File ruta = new File(jtxtIDTren.getText());
        this.AgregarTrenRegistro(ruta);
    }//GEN-LAST:event_jbtnGuardarDatosTrenActionPerformed

    private void jBtnRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRecorridoActionPerformed
        registroRecorridos regRecorridos = new registroRecorridos();
        regRecorridos.setVisible(true);
        this.setVisible(false);
        moduloRegistro.registroRecorridos.jlblTituloRegistroTrenes.setText("Registro de Recorridos");
    }//GEN-LAST:event_jBtnRecorridoActionPerformed

    private void jBtnRegTrenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegTrenesActionPerformed
      this.setVisible(true);
      jlblTituloRegistroTrenes.setText("Registro de Trenes");
    }//GEN-LAST:event_jBtnRegTrenesActionPerformed

    private void jBtnRegEstacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegEstacionesActionPerformed
        registroEstaciones Estaciones = new registroEstaciones();
        Estaciones.setVisible(true);
        this.setVisible(false);
        moduloRegistro.registroEstaciones.jlblTituloRegistroTrenes.setText("Registro de Estaciones");
    }//GEN-LAST:event_jBtnRegEstacionesActionPerformed

    private void jbtnLimpiarTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLimpiarTrenActionPerformed
        Limpiar_txt lp = new Limpiar_txt();
        lp.limpiar_texto(jPnlInfoRegRecorridos);
        
        jtxtIDTren.setText("");
        jtxtCapacidadTren.setText("");
        jtxtCantVagones.setText("");
    }//GEN-LAST:event_jbtnLimpiarTrenActionPerformed

    private void jTableDatosTrenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDatosTrenMouseClicked
              clic_tabla = jTableDatosTren.rowAtPoint(evt.getPoint());
        
        int ID = (int)jTableDatosTren.getValueAt(clic_tabla, 0);
        int vagones = (int)jTableDatosTren.getValueAt(clic_tabla, 1);
        double capacidad = (double)jTableDatosTren.getValueAt(clic_tabla, 2);
        
        jtxtIDTren.setText(String.valueOf(ID));
        jtxtCantVagones.setText(String.valueOf(vagones));
        jtxtCapacidadTren.setText(String.valueOf(capacidad));
        
        
        try{
//            JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 4);
//            lblFoto.setIcon(lbl.getIcon());
        }catch(Exception ex){
        }
    }//GEN-LAST:event_jTableDatosTrenMouseClicked

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
            java.util.logging.Logger.getLogger(registroTrenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroTrenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroTrenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroTrenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registroTrenes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConsulta;
    private javax.swing.JButton jBtnHome;
    private javax.swing.JButton jBtnRecorrido;
    private javax.swing.JButton jBtnRegEstaciones;
    private javax.swing.JButton jBtnRegFuncionario;
    private javax.swing.JButton jBtnRegRecorrido;
    private javax.swing.JButton jBtnRegTrenes;
    private javax.swing.JButton jBtnReportes;
    private javax.swing.JButton jBtnReservarEspacio;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPnlInfoRegRecorridos;
    private javax.swing.JPanel jPnlMenuRecorrido;
    private javax.swing.JPanel jPnlNavBar;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableDatosTren;
    private javax.swing.JButton jbtnEliminarDatosTren;
    private javax.swing.JButton jbtnGuardarDatosTren;
    private javax.swing.JButton jbtnLimpiarTren;
    private javax.swing.JButton jbtnMenuDesplegable;
    private javax.swing.JButton jbtnModificarDatosTren;
    private javax.swing.JLabel jlblCantVagones;
    private javax.swing.JLabel jlblCapaTren;
    private javax.swing.JLabel jlblIDTren;
    public static javax.swing.JLabel jlblTituloRegistroTrenes;
    public static javax.swing.JTextPane jtxtCantVagones;
    public static javax.swing.JTextPane jtxtCapacidadTren;
    public static javax.swing.JTextPane jtxtIDTren;
    // End of variables declaration//GEN-END:variables
}
