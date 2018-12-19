/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moduloRegistro;
import Clases.Limpiar_txt;
import Clases.ProcRMaquinistas;
import Clases.RegistroMaquinistas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import static moduloRegistro.registroRecorridos.jCbED;
import static moduloRegistro.registroRecorridos.jCbIDES;
import static moduloRegistro.registroRecorridos.jtxtIDRuta;


/**
 *
 * @author Usuario
 */
public class menuRegistro extends javax.swing.JFrame {

    
    Limpiar_txt lt = new Limpiar_txt();
    
    String barra = File.separator; //File.separator lo que hace es \\
    private String ruta_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\BDtxt\\RegistroMaquinistas.txt";
    private String rutaIndividual_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\txtIndiviual\\Maquinistas\\Maquinistas";
    
    RegistroMaquinistas RegMaquinista;
    ProcRMaquinistas PRMaquinista;
    
    int clic_tabla;
    registroRecorridos ddl = new registroRecorridos();
    
    public menuRegistro() {
        initComponents();
        this.setLocationRelativeTo(null);
       
         PRMaquinista = new ProcRMaquinistas();
        
         ddl.cargarDDLRecorrido();
        
         try {
            cargar_txt();
            listarRegistro();
        } catch (Exception e) {
            mensaje("No existe el archivo txt");
        }
    }
    
    
     public void AgregarRegistro(File ruta){
        try{
            if(leerIDMaquinista()== -666)mensaje("Ingresar ID en número entero");
            else if(leerNMaquinista()== null)mensaje("Ingresar Nombre de Maquinista");
            else if(leerRecorrido()== null)mensaje("Seleccione el recorrido");
            else if(leerDescripcion()== null) mensaje("Digite una descripción");
           
            else{
                RegMaquinista = new RegistroMaquinistas(leerIDMaquinista(), leerNMaquinista(), leerRecorrido(), leerDescripcion());
                if(PRMaquinista.BuscarID(RegMaquinista.getIdMaquinista())!= -1)mensaje("Este ID ya existe");
                else PRMaquinista.AgregarMaquinista(RegMaquinista);
                
                CrearTXTIndMaquinistas();
                
                
                Guardar_txt();
                listarRegistro();
                lt.limpiar_texto(jPnlMenuRegistroFunc); 
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
     
         
        public void Guardar_txt(){
        String line = System.getProperty("line.separator"); 
        try{
            BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(ruta_txt)));
            for(int i = 0; i < PRMaquinista.cantidadMaquinistas(); i++){
                RegMaquinista = PRMaquinista.ObtenerMaquinista(i);
                ficheroSalida.write(String.valueOf(RegMaquinista.getIdMaquinista()+"," +RegMaquinista.getNombreMaquinista()+", "+RegMaquinista.getRecorrido()+", "+RegMaquinista.getDescripcion()));
              
                ficheroSalida.newLine();
            }
             ficheroSalida.close();
            
        }catch(Exception ex){
            mensaje("Error al guardar archivo:" + ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
        public void cargar_txt(){
        File ruta = new File(ruta_txt);
        String line = System.getProperty("line.separator"); 
        try{
            
            FileReader ficheroSalida = new FileReader(ruta);
            BufferedReader BR = new BufferedReader(ficheroSalida);
            
            String linea = null;
            while((linea = BR.readLine())!= null){
                StringTokenizer St = new StringTokenizer(linea, ",");
                RegMaquinista = new RegistroMaquinistas();
                RegMaquinista.setIdMaquinista(Integer.parseInt(St.nextToken()));
                RegMaquinista.setNombreMaquinista(St.nextToken());
                RegMaquinista.setRecorrido(St.nextToken());
                RegMaquinista.setDescripcion(String.valueOf(St.nextToken()));
                PRMaquinista.AgregarMaquinista(RegMaquinista);
            }
            BR.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: " +ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
        
        public void modificarRegistro(File ruta){
        try{
            
            File dir = new File(ruta_txt);
            File url = new File(rutaIndividual_txt + jtxtIDRuta.getText()+".maquinistas");

             if(jtxtIDRuta.getText().equals(""))
           {
               JOptionPane.showMessageDialog(rootPane, "INIDIQUE EL REGISTRO");
           }
             else
             {
                if(dir.exists() && url.exists())
                {
                    if(leerIDMaquinista()== -666)mensaje("Ingresar ID en número entero");
                    else if(leerNMaquinista()== null)mensaje("Ingresar Nombre de la Ruta");
                    else if(leerRecorrido()== null)mensaje("Ingresar Precio");
                    else if(leerDescripcion()== null) mensaje("Elija estación Salida");
                    
                    else{
                        int ID = PRMaquinista.BuscarID(leerIDMaquinista());
                        RegMaquinista = new RegistroMaquinistas(leerIDMaquinista(), leerNMaquinista(), leerRecorrido(), leerDescripcion());

                        if(ID == -1)PRMaquinista.AgregarMaquinista(RegMaquinista);
                        else PRMaquinista.ModificarMaquinista(ID, RegMaquinista);

                        EditarTXTIndMaquinista();

                        Guardar_txt();
                        listarRegistro();
                        lt.limpiar_texto(jPnlRegFuncionario);
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
    
        public void eliminarRegistro(){
        try{
            if(leerIDMaquinista()== -666) mensaje("Ingrese ID entero");
            
            else{
                int ID = PRMaquinista.BuscarID(leerIDMaquinista());
                if(ID == -1) mensaje("codigo no existe");
                
                else{
                    int si = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este la Estación","Si / No",0);
                    if(si == 0){
                        PRMaquinista.EliminarMaquinista(ID);
                        EliminarTXTIndMaquinista();
                        JOptionPane.showMessageDialog(rootPane, "Registro Eliminado");
                        
                        Guardar_txt();
                        listarRegistro();
                        lt.limpiar_texto(this.jPnlRegFuncionario);
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
        dt.addColumn("Nombre Maquinista");
        dt.addColumn("Recorrido");
        dt.addColumn("Descripcion");
        //jTableDatosEstacion.setDefaultRenderer(Object.class, null);
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < PRMaquinista.cantidadMaquinistas(); i++){
            RegMaquinista = PRMaquinista.ObtenerMaquinista(i);
            fila[0] = RegMaquinista.getIdMaquinista();
            fila[1] = RegMaquinista.getNombreMaquinista();
            fila[2] = RegMaquinista.getRecorrido();
            fila[3] = RegMaquinista.getDescripcion();
            dt.addRow(fila);
        }
        jTableDatosMaquinista.setModel(dt);
        jTableDatosMaquinista.setRowHeight(30);
    }
         
         
         
    public int leerIDMaquinista(){
        try{
            int IDM = Integer.parseInt(jtxtIDM.getText().trim());
            return IDM;
        }catch(Exception ex){
            return -666;
        }
    }
     
    public String leerNMaquinista(){
        try{
            String nombreM = jtxtNombreMaquinista.getText().trim().replace(" ", " ");
            return nombreM;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerRecorrido(){
        try{
            String recorrido = (String)jDdlDestino.getSelectedItem();
            return recorrido;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerDescripcion(){
        try{
           String descripcion = jTextAreaDescrip.getText();
            return descripcion;
        }catch(Exception ex)
        {
            return null;
        }
    }
   
    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
        
     private void CrearTXTIndMaquinistas()
   {
           String archivo = jtxtIDM.getText()+".maquinistas";
           File crear_archivo = new File(rutaIndividual_txt + archivo);
           
           if(jtxtIDM.getText().equals(""))
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
                        //crear_ubicacion.mkdirs();
                        Formatter crear = new Formatter(rutaIndividual_txt + archivo);
                        crear.format("%s\r\n%s\r\n%s\r\n%s", 
                                "IDMaquinista="+jtxtIDM.getText(),
                                "Nombre Maquinista="+jtxtNombreMaquinista.getText(), 
                                "Recorrido="+jDdlDestino.getItemAt(archivo.compareTo(archivo)), 
                                "Descripcion="+jTextAreaDescrip.getText());
                        crear.close();
                    }
               } 
               catch (Exception e)
               {
                   
               }
           } 
       }
     
     private void EditarTXTIndMaquinista()
       {
           File url = new File(rutaIndividual_txt + jtxtIDRuta.getText()+".maquinistas");
           String archivo = jtxtIDRuta.getText()+".recorridos";
           if(jtxtIDRuta.getText().equals(""))
           {
               JOptionPane.showMessageDialog(rootPane, "INIDIQUE EL REGISTRO");
           }
           else
           {
               if(url.exists())
               {
                   try 
                    {
                     FileWriter escrito = new FileWriter(rutaIndividual_txt + jtxtIDRuta.getText()+".maquinistas");
                     String ID = "IDMaquinista=";
                     String NombreM =  "Nombre Maquinista=";
                     String Recorrido = "Recorrido=";
                     String Descrip = "Descripcion=";
                     
                        PrintWriter guardar = new PrintWriter(escrito);
                        guardar.println(ID+jtxtIDRuta.getText());
                        guardar.println(NombreM+jtxtNombreMaquinista.getText());
                        guardar.println(Recorrido+jTextAreaDescrip.getText());
                        guardar.println(Descrip+jDdlDestino.getItemAt(archivo.compareTo(archivo)));
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
    
     private void EliminarTXTIndMaquinista()
       {
            File url = new File(rutaIndividual_txt + jtxtIDRuta.getText()+".maquinistas");
            if(jtxtIDRuta.getText().equals(""))
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
   
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelFondo = new javax.swing.JPanel();
        jPnlNavBar = new javax.swing.JPanel();
        jlblTituloRegistro = new javax.swing.JLabel();
        jbtnMenuDesplegable = new javax.swing.JButton();
        jPnlRegFuncionario = new javax.swing.JPanel();
        jPanelOpciones = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableDatosMaquinista = new javax.swing.JTable();
        jlblID = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtxtIDM = new javax.swing.JTextPane();
        jlblNomConductor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtNombreMaquinista = new javax.swing.JTextPane();
        jlblRutaDestino = new javax.swing.JLabel();
        jDdlDestino = new javax.swing.JComboBox<>();
        jlblDescripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescrip = new javax.swing.JTextArea();
        jbtnGuardarDatos = new javax.swing.JButton();
        jbtnModificarDatos = new javax.swing.JButton();
        jbtnEliminarDatos = new javax.swing.JButton();
        jbtnLimpiar = new javax.swing.JButton();
        jPnlMenuRegistroFunc = new javax.swing.JPanel();
        jBtnHome = new javax.swing.JButton();
        jBtnReportes = new javax.swing.JButton();
        jBtnReservarEspacio = new javax.swing.JButton();
        jBtnConsulta = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jBtnRegFuncionario = new javax.swing.JButton();
        jBtnRecorrido = new javax.swing.JButton();
        jBtnRegTrenes = new javax.swing.JButton();
        jBtnRegEstaciones = new javax.swing.JButton();
        jBtnRegRecorrido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelFondo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanelFondo.setLayout(new java.awt.GridBagLayout());

        jPnlNavBar.setBackground(new java.awt.Color(0, 102, 204));
        jPnlNavBar.setToolTipText("");
        jPnlNavBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblTituloRegistro.setBackground(new java.awt.Color(0, 0, 0));
        jlblTituloRegistro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblTituloRegistro.setForeground(new java.awt.Color(0, 0, 0));
        jlblTituloRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPnlNavBar.add(jlblTituloRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 240, 30));

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

        jPnlRegFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        jPnlRegFuncionario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelOpciones.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelOpciones.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableDatosMaquinista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre Conductor", "Ruta", "Descripción"
            }
        ));
        jTableDatosMaquinista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDatosMaquinistaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableDatosMaquinista);

        jPanelOpciones.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 220));

        jPnlRegFuncionario.add(jPanelOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 800, 220));

        jlblID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblID.setForeground(new java.awt.Color(0, 0, 0));
        jlblID.setText("ID ");
        jPnlRegFuncionario.add(jlblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 30, 29));

        jScrollPane3.setViewportView(jtxtIDM);

        jPnlRegFuncionario.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 180, -1));

        jlblNomConductor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNomConductor.setForeground(new java.awt.Color(0, 0, 0));
        jlblNomConductor.setText("Nombre del conductor: ");
        jPnlRegFuncionario.add(jlblNomConductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, 29));

        jScrollPane1.setViewportView(jtxtNombreMaquinista);

        jPnlRegFuncionario.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 280, -1));

        jlblRutaDestino.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblRutaDestino.setForeground(new java.awt.Color(0, 0, 0));
        jlblRutaDestino.setText("Ruta/Destino: ");
        jPnlRegFuncionario.add(jlblRutaDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 29));

        jDdlDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDdlDestinoActionPerformed(evt);
            }
        });
        jPnlRegFuncionario.add(jDdlDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 220, -1));

        jlblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        jlblDescripcion.setText("Descripción:");
        jPnlRegFuncionario.add(jlblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 110, 20));

        jTextAreaDescrip.setColumns(20);
        jTextAreaDescrip.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDescrip);

        jPnlRegFuncionario.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 330, 70));

        jbtnGuardarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Save Archive_26px.png"))); // NOI18N
        jbtnGuardarDatos.setText("Guardar");
        jbtnGuardarDatos.setFocusable(false);
        jbtnGuardarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarDatosActionPerformed(evt);
            }
        });
        jPnlRegFuncionario.add(jbtnGuardarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 130, -1));

        jbtnModificarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Edit File_26px.png"))); // NOI18N
        jbtnModificarDatos.setText("Modificar");
        jbtnModificarDatos.setFocusable(false);
        jbtnModificarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarDatosActionPerformed(evt);
            }
        });
        jPnlRegFuncionario.add(jbtnModificarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 130, -1));

        jbtnEliminarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Trash_26px.png"))); // NOI18N
        jbtnEliminarDatos.setText("Eliminar");
        jbtnEliminarDatos.setFocusable(false);
        jbtnEliminarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarDatosActionPerformed(evt);
            }
        });
        jPnlRegFuncionario.add(jbtnEliminarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 120, -1));

        jbtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Disposal_26px.png"))); // NOI18N
        jbtnLimpiar.setToolTipText("Limpiar información");
        jPnlRegFuncionario.add(jbtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 40, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.weightx = 1.9;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(75, 18, 19, 0);
        jPanelFondo.add(jPnlRegFuncionario, gridBagConstraints);

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
        jBtnReservarEspacio.setText("RESERVA ESPACIO");
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

        javax.swing.GroupLayout jPnlMenuRegistroFuncLayout = new javax.swing.GroupLayout(jPnlMenuRegistroFunc);
        jPnlMenuRegistroFunc.setLayout(jPnlMenuRegistroFuncLayout);
        jPnlMenuRegistroFuncLayout.setHorizontalGroup(
            jPnlMenuRegistroFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnReportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnReservarEspacio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addComponent(jBtnRegFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(jBtnRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRegTrenes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRegEstaciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRegRecorrido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnlMenuRegistroFuncLayout.setVerticalGroup(
            jPnlMenuRegistroFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlMenuRegistroFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnReservarEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRegFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRegTrenes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRegRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRegEstaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 5);
        jPanelFondo.add(jPnlMenuRegistroFunc, gridBagConstraints);

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
        int posicion = this.jPnlMenuRegistroFunc.getX();
        if(posicion > -1)
        {
        Animacion.Animacion.mover_izquierda(0, -210, 2, 2, jPnlMenuRegistroFunc);
        }
        else
        {
        Animacion.Animacion.mover_derecha(-210, 12, 2, 2, jPnlMenuRegistroFunc);
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

    private void jBtnRegFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegFuncionarioActionPerformed
            this.setVisible(true);
            jlblTituloRegistro.setText("Registro de Funcionarios");
    }//GEN-LAST:event_jBtnRegFuncionarioActionPerformed

    private void jbtnModificarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarDatosActionPerformed
         File ruta = new File(jtxtIDM.getText());
        this.modificarRegistro(ruta);
    }//GEN-LAST:event_jbtnModificarDatosActionPerformed

    private void jbtnGuardarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarDatosActionPerformed
        File ruta = new File(jtxtIDM.getText());
        this.AgregarRegistro(ruta);
    }//GEN-LAST:event_jbtnGuardarDatosActionPerformed

    private void jDdlDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDdlDestinoActionPerformed
       String copiar = (String) jDdlDestino.getSelectedItem();
         jDdlDestino.setName(String.valueOf(jDdlDestino.getSelectedObjects()));
    }//GEN-LAST:event_jDdlDestinoActionPerformed

    private void jbtnEliminarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarDatosActionPerformed
       eliminarRegistro();
    }//GEN-LAST:event_jbtnEliminarDatosActionPerformed

    private void jBtnRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRecorridoActionPerformed
        
    }//GEN-LAST:event_jBtnRecorridoActionPerformed

    private void jBtnRegTrenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegTrenesActionPerformed
        registroTrenes Trenes = new registroTrenes();
        Trenes.setVisible(true);
        this.setVisible(false);
         moduloRegistro.registroTrenes.jlblTituloRegistroTrenes.setText("Registro de Trenes");
    }//GEN-LAST:event_jBtnRegTrenesActionPerformed

    private void jBtnRegEstacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegEstacionesActionPerformed
        registroEstaciones Estaciones = new registroEstaciones();
        Estaciones.setVisible(true);
        this.setVisible(false);
        moduloRegistro.registroEstaciones.jlblTituloRegistroTrenes.setText("Registro de Estaciones");
    }//GEN-LAST:event_jBtnRegEstacionesActionPerformed

    private void jBtnRegRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegRecorridoActionPerformed
        registroRecorridos regRecorridos = new registroRecorridos();
        regRecorridos.setVisible(true);
        this.setVisible(false);
        moduloRegistro.registroRecorridos.jlblTituloRegistroTrenes.setText("Registro de Recorridos");
    }//GEN-LAST:event_jBtnRegRecorridoActionPerformed

    private void jTableDatosMaquinistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDatosMaquinistaMouseClicked
        clic_tabla = jTableDatosMaquinista.rowAtPoint(evt.getPoint());
        
        int IDM = (int)jTableDatosMaquinista.getValueAt(clic_tabla, 0);
        String nombreM = ""+jTableDatosMaquinista.getValueAt(clic_tabla, 1);
        String recorrido  = ""+jTableDatosMaquinista.getValueAt(clic_tabla, 2);
        String descripcion = ""+jTableDatosMaquinista.getValueAt(clic_tabla, 3);
        
        jtxtIDRuta.setText(String.valueOf(IDM));
        jtxtNombreMaquinista.setText(nombreM);
        jDdlDestino.setName(String.valueOf(recorrido));
        jTextAreaDescrip.setText(descripcion);
        
        try{
//            JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 4);
//            lblFoto.setIcon(lbl.getIcon());
        }catch(Exception ex){
        }
    }//GEN-LAST:event_jTableDatosMaquinistaMouseClicked

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
            java.util.logging.Logger.getLogger(menuRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuRegistro().setVisible(true);
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
    public static javax.swing.JComboBox<String> jDdlDestino;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPnlMenuRegistroFunc;
    private javax.swing.JPanel jPnlNavBar;
    private javax.swing.JPanel jPnlRegFuncionario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableDatosMaquinista;
    private javax.swing.JTextArea jTextAreaDescrip;
    private javax.swing.JButton jbtnEliminarDatos;
    private javax.swing.JButton jbtnGuardarDatos;
    private javax.swing.JButton jbtnLimpiar;
    private javax.swing.JButton jbtnMenuDesplegable;
    private javax.swing.JButton jbtnModificarDatos;
    private javax.swing.JLabel jlblDescripcion;
    private javax.swing.JLabel jlblID;
    private javax.swing.JLabel jlblNomConductor;
    private javax.swing.JLabel jlblRutaDestino;
    public static javax.swing.JLabel jlblTituloRegistro;
    private javax.swing.JTextPane jtxtIDM;
    private javax.swing.JTextPane jtxtNombreMaquinista;
    // End of variables declaration//GEN-END:variables
}
