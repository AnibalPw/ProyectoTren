/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moduloRegistro;
import Clases.Limpiar_txt;
import Clases.ProcREstaciones;
import Clases.RegistroEstaciones;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static moduloRegistro.registroRecorridos.jCbED;
import static moduloRegistro.registroRecorridos.jCbIDES;


/**
 *
 * @author Aníbal Pérez Wolf
 */
public class registroEstaciones extends javax.swing.JFrame {


    Limpiar_txt lt = new Limpiar_txt();
    
    String barra = File.separator; //File.separator lo que hace es \\
    private String ruta_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\BDtxt\\RegistroEstaciones.txt";
    private String rutaIndividual_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\txtIndiviual\\Estaciones\\Estaciones";
    private String estaciones = System.getProperty("user.dir")+barra+"txtIndiviual"+barra+"Estaciones";
    
    File contenedor = new File(estaciones);
    
    File [] registros = contenedor.listFiles();
    
    RegistroEstaciones RegEstacion;
    ProcREstaciones PREstaciones;
    
     int clic_tabla;
    
    public registroEstaciones() {
        initComponents();
        this.setLocationRelativeTo(null);
        PREstaciones = new ProcREstaciones();
        
        try {
            cargar_txt();
            listarRegistro();
        } catch (Exception e) {
            mensaje("No existe el archivo txt");
        }
       
    }
     
        public void cargarDDLSalida()
    {
        for(int i = 0; i<registros.length; i++)
        {
            jCbIDES.addItem(registros[i].getName().replace(".estacion", ""));
        }
    }
     
       public void cargarDDLEntrada()
    {
        for(int i = 0; i<registros.length; i++)
        {
            jCbED.addItem(registros[i].getName().replace(".estacion", ""));
        }
    }
      
    
    public void AgregarRegistro(File ruta){
        try{
            if(leerID() == -666)mensaje("Ingresar ID en número entero");
            else if(leerNombre() == null)mensaje("Ingresar Nombre de la Estación");
            else if(leerUbicacion() == null)mensaje("Ingresar Ubicación");
            else if(leerHoraEntrada() == null) mensaje("Ingresar Hora de Entrada");
            else if(leerHoraSalida() == null)mensaje("Ingresar Hora Salida");
            else if(leerNumTel() == null)mensaje("Ingresar Número de Télefono");
            else if(leerCorreo() == null)mensaje("Ingrese Correo");
            else{
                RegEstacion = new RegistroEstaciones(leerID(), leerNombre(), leerUbicacion(), leerHoraEntrada(), leerHoraSalida(), leerNumTel(), leerCorreo());
                if(PREstaciones.BuscarID(RegEstacion.getIdEstacion())!= -1)mensaje("Este ID ya existe");
                else PREstaciones.AgregarRegistro(RegEstacion);
                
                CrearTXTIndEstacion();
                
                Guardar_txt();
                listarRegistro();
                lt.limpiar_texto(jPnlInfoRegEstaciones); 
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
    public void cargar_txt(){
        File ruta = new File(ruta_txt);
        try{
            
            FileReader ficheroSalida = new FileReader(ruta);
            BufferedReader BR = new BufferedReader(ficheroSalida);
            
            
            String linea = null;
            while((linea = BR.readLine())!=null){
                StringTokenizer St = new StringTokenizer(linea, ",");
                RegEstacion = new RegistroEstaciones();
                RegEstacion.setIdEstacion(Integer.parseInt(St.nextToken()));
                RegEstacion.setNombre(St.nextToken());
                RegEstacion.setUbicacion(St.nextToken());
                RegEstacion.setHoraEntrada(St.nextToken());
                RegEstacion.setHoraSalida(St.nextToken());
                RegEstacion.setNumTelefono(St.nextToken());
                RegEstacion.setCorreo(St.nextToken());
                PREstaciones.AgregarRegistro(RegEstacion);
            }
            BR.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
    public void Guardar_txt(){
        try{
            BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(ruta_txt)));
            for(int i = 0; i < PREstaciones.cantidadRegistro(); i++){
                RegEstacion = PREstaciones.ObtenerRegistro(i);
//                pw.println(String.valueOf(RegEstacion.getIdEstacion()+", "+RegEstacion.getNombre()+", "+RegEstacion.getUbicacion()+", "+RegEstacion.getHoraEntrada()+",  "+RegEstacion.getHoraSalida()+","+RegEstacion.getNumTelefono()+","+RegEstacion.getCorreo()));
                ficheroSalida.write(String.valueOf(RegEstacion.getIdEstacion()+", "+RegEstacion.getNombre()+", "+RegEstacion.getUbicacion()+", "+RegEstacion.getHoraEntrada()+",  "+RegEstacion.getHoraSalida()+","+RegEstacion.getNumTelefono()+","+RegEstacion.getCorreo()));
                ficheroSalida.newLine();
            }
             ficheroSalida.close();
            
        }catch(Exception ex){
            mensaje("Error al guardar archivo:" + ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void modificarRegistro(File ruta){
        try{
            
            File dir = new File(ruta_txt);
            File url = new File(rutaIndividual_txt + jtxtIDEstacion.getText()+".estacion");

             if(jtxtIDEstacion.getText().equals(""))
           {
               JOptionPane.showMessageDialog(rootPane, "INIDIQUE EL REGISTRO");
           }
             else
             {
                if(dir.exists() && url.exists())
                {
                    if(leerID() == -666)mensaje("Ingresar ID en número entero");
                    else if(leerNombre() == null)mensaje("Ingresar Nombre de la Estación");
                    else if(leerUbicacion() == null)mensaje("Ingresar Ubicación");
                    else if(leerHoraEntrada() == null) mensaje("Ingresar Hora de Entrada");
                    else if(leerHoraSalida() == null)mensaje("Ingresar Hora Salida");
                    else if(leerNumTel() == null)mensaje("Ingresar Número de Télefono");
                    else if(leerCorreo() == null)mensaje("Ingrese Correo");
                    else{
                        int ID = PREstaciones.BuscarID(leerID());
                        RegEstacion = new RegistroEstaciones(leerID(), leerNombre(), leerUbicacion(), leerHoraEntrada(), leerHoraSalida(), leerNumTel(), leerCorreo());

                        if(ID == -1)PREstaciones.AgregarRegistro(RegEstacion);
                        else PREstaciones.ModificarRegistro(ID, RegEstacion);

                        EditarTXTIndEstacion();

                        Guardar_txt();
                        listarRegistro();
                        lt.limpiar_texto(jPnlInfoRegEstaciones);
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
            if(leerID() == -666) mensaje("Ingrese ID entero");
            
            else{
                int ID = PREstaciones.BuscarID(leerID());
                if(ID == -1) mensaje("codigo no existe");
                
                else{
                    int si = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este la Estación","Si / No",0);
                    if(si == 0){
                        PREstaciones.EliminarRegistro(ID);
                        EliminarTXTIndEstacion();
                        
                        Guardar_txt();
                        listarRegistro();
                        lt.limpiar_texto(this.jPnlInfoRegEstaciones);
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
        dt.addColumn("Nombre");
        dt.addColumn("Ubicacion");
        dt.addColumn("Horario Entrada");
        dt.addColumn("Horario Salida");
        dt.addColumn("Télefono");
        dt.addColumn("Correo");
        
        //jTableDatosEstacion.setDefaultRenderer(Object.class, null);
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < PREstaciones.cantidadRegistro(); i++){
            RegEstacion = PREstaciones.ObtenerRegistro(i);
            fila[0] = RegEstacion.getIdEstacion();
            fila[1] = RegEstacion.getNombre();
            fila[2] = RegEstacion.getUbicacion();
            fila[3] = RegEstacion.getHoraEntrada();
            fila[4] = RegEstacion.getHoraSalida();
            fila[5] = RegEstacion.getNumTelefono();
            fila[6] = RegEstacion.getCorreo();
            dt.addRow(fila);
        }
        jTableDatosEstacion.setModel(dt);
        jTableDatosEstacion.setRowHeight(30);
    }
    
    public int leerID(){
        try{
            int ID = Integer.parseInt(jtxtIDEstacion.getText().trim());
            return ID;
        }catch(Exception ex){
            return -666;
        }
    }
     
    public String leerNombre(){
        try{
            String nombre = jtxtNombreEstacion.getText().trim().replace(" ", " ");
            return nombre;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerUbicacion(){
        try{
           String ubicacion = jtxtUbicacion.getText().trim().replace(" ", " ");
            return ubicacion;
        }catch(Exception ex)
        {
            return null;
        }
    }
    
    
    public String leerHoraEntrada(){
        try{
            String HEntrada = jtxtHEntrada.getText().trim().replace(" ", " ");
            return HEntrada;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerHoraSalida(){
        try{
           String HSalida = jtxtHSalida.getText().trim().replace(" ", " ");
            return HSalida;
        }catch(Exception ex)
        {
            return null;
        }
    }
    
    
    public String leerNumTel(){
        try{
            String NumTel = jtxtNumTel.getText().trim().replace(" ", " ");
            return NumTel;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerCorreo(){
        try{
           String Correo = jtxtCorreo.getText().trim().replace(" ", " ");
            return Correo;
        }catch(Exception ex)
        {
            return null;
        }
    }
   
    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
        
    
       
    private void CrearTXTIndEstacion()
       {
           String archivo = jtxtIDEstacion.getText()+".estacion";
           //File crear_ubicacion = new File(rutaIndividual_txt);
           File crear_archivo = new File(rutaIndividual_txt + archivo);
           
           if(jtxtIDEstacion.getText().equals(""))
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
                        crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s", 
                                "ID="+jtxtIDEstacion.getText(),
                                "Nombre="+jtxtNombreEstacion.getText(), 
                                "Ubiciación="+jtxtUbicacion.getText(), 
                                "HoraEntrada="+jtxtHEntrada.getText(), 
                                "HoraSalida="+jtxtHSalida.getText(), 
                                "NumTel="+jtxtNumTel.getText(), 
                                "Correo="+jtxtCorreo.getText());
                        crear.close();
                        
                        jCbED.removeAllItems();
                        registros = contenedor.listFiles();
                        cargarDDLEntrada();
                        jCbIDES.removeAllItems();
                        registros = contenedor.listFiles();
                        cargarDDLSalida();
                    }
               } 
               catch (Exception e)
               {
                   
               }
           } 
       }
       
       private void EditarTXTIndEstacion()
       {
           File url = new File(rutaIndividual_txt + jtxtIDEstacion.getText()+".estacion");
           if(jtxtIDEstacion.getText().equals(""))
           {
               JOptionPane.showMessageDialog(rootPane, "INIDIQUE EL REGISTRO");
           }
           else
           {
               if(url.exists())
               {
                   try 
                    {
                     FileWriter escrito = new FileWriter(rutaIndividual_txt + jtxtIDEstacion.getText()+".estacion");
                     String ID = "ID=";
                     String Nombre = "Nombre=";
                     String Ubi = "Ubiciación=";
                     String HoraE = "HoraEntrada=";
                     String HoraS = "HoraSalida=";
                     String Ntel = "NumTel=";
                     String Correo = "Correo=";
                     
                        PrintWriter guardar = new PrintWriter(escrito);
                        guardar.println(ID+jtxtIDEstacion.getText());
                        guardar.println(Nombre+jtxtNombreEstacion.getText());
                        guardar.println(Ubi+jtxtUbicacion.getText());
                        guardar.println(HoraE+jtxtHEntrada.getText());
                        guardar.println(HoraS+jtxtHSalida.getText());
                        guardar.println(Ntel+jtxtNumTel.getText());
                        guardar.println(Correo+jtxtCorreo.getText());
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
       
       private void EliminarTXTIndEstacion()
       {
            File url = new File(rutaIndividual_txt + jtxtIDEstacion.getText()+".estacion");
            if(jtxtIDEstacion.getText().equals(""))
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
                        jCbED.removeItem(jtxtIDEstacion.getText());
                        jCbIDES.removeItem(jtxtIDEstacion.getText());
                        
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
        jlblTituloRegistroTrenes = new javax.swing.JLabel();
        jbtnMenuDesplegable = new javax.swing.JButton();
        jPnlInfoRegEstaciones = new javax.swing.JPanel();
        jPanelOpciones = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableDatosEstacion = new javax.swing.JTable();
        jlblUbicacion = new javax.swing.JLabel();
        jlblHSalida = new javax.swing.JLabel();
        jbtnGuardarDatosTren = new javax.swing.JButton();
        jbtnModificarDatosTren = new javax.swing.JButton();
        jbtnEliminarDatosTren = new javax.swing.JButton();
        jbtnLimpiarTren = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtxtHSalida = new javax.swing.JTextPane();
        jlblHEntrada = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtxtHEntrada = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtxtUbicacion = new javax.swing.JTextPane();
        jlblIDEstacion = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtxtIDEstacion = new javax.swing.JTextPane();
        jlblNumTel = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtxtNumTel = new javax.swing.JTextPane();
        jlblHSalida1 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtxtCorreo = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        jtxtNombreEstacion = new javax.swing.JTextPane();
        jlblNombreEstacion = new javax.swing.JLabel();
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
        jPnlNavBar.add(jlblTituloRegistroTrenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 240, 20));

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

        jPnlInfoRegEstaciones.setBackground(new java.awt.Color(255, 255, 255));
        jPnlInfoRegEstaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelOpciones.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelOpciones.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableDatosEstacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID de Estación", "Nombre", "Ubicación", "Horario de Salida", "Horario de Entrada", "Télefono ", "Correo"
            }
        ));
        jTableDatosEstacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDatosEstacionMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableDatosEstacion);

        jPanelOpciones.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 240));

        jPnlInfoRegEstaciones.add(jPanelOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 1000, 240));

        jlblUbicacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblUbicacion.setForeground(new java.awt.Color(0, 0, 0));
        jlblUbicacion.setText("Ubicación:");
        jPnlInfoRegEstaciones.add(jlblUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, 29));

        jlblHSalida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblHSalida.setForeground(new java.awt.Color(0, 0, 0));
        jlblHSalida.setText("Horario de Salida:");
        jPnlInfoRegEstaciones.add(jlblHSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 130, 20));

        jbtnGuardarDatosTren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Save Archive_26px.png"))); // NOI18N
        jbtnGuardarDatosTren.setText("Guardar");
        jbtnGuardarDatosTren.setFocusable(false);
        jbtnGuardarDatosTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarDatosTrenActionPerformed(evt);
            }
        });
        jPnlInfoRegEstaciones.add(jbtnGuardarDatosTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 130, -1));

        jbtnModificarDatosTren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Edit File_26px.png"))); // NOI18N
        jbtnModificarDatosTren.setText("Modificar");
        jbtnModificarDatosTren.setFocusable(false);
        jbtnModificarDatosTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarDatosTrenActionPerformed(evt);
            }
        });
        jPnlInfoRegEstaciones.add(jbtnModificarDatosTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 130, -1));

        jbtnEliminarDatosTren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Trash_26px.png"))); // NOI18N
        jbtnEliminarDatosTren.setText("Eliminar");
        jbtnEliminarDatosTren.setFocusable(false);
        jbtnEliminarDatosTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarDatosTrenActionPerformed(evt);
            }
        });
        jPnlInfoRegEstaciones.add(jbtnEliminarDatosTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 120, -1));

        jbtnLimpiarTren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Disposal_26px.png"))); // NOI18N
        jbtnLimpiarTren.setToolTipText("Limpiar información");
        jbtnLimpiarTren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLimpiarTrenActionPerformed(evt);
            }
        });
        jPnlInfoRegEstaciones.add(jbtnLimpiarTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 190, 40, -1));

        jScrollPane6.setViewportView(jtxtHSalida);

        jPnlInfoRegEstaciones.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 250, -1));

        jlblHEntrada.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblHEntrada.setForeground(new java.awt.Color(0, 0, 0));
        jlblHEntrada.setText("Horario de Entrada:");
        jPnlInfoRegEstaciones.add(jlblHEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 140, 20));

        jScrollPane7.setViewportView(jtxtHEntrada);

        jPnlInfoRegEstaciones.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 250, -1));

        jScrollPane8.setViewportView(jtxtUbicacion);

        jPnlInfoRegEstaciones.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 250, -1));

        jlblIDEstacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblIDEstacion.setForeground(new java.awt.Color(0, 0, 0));
        jlblIDEstacion.setText("ID de Estación:");
        jPnlInfoRegEstaciones.add(jlblIDEstacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 29));

        jScrollPane9.setViewportView(jtxtIDEstacion);

        jPnlInfoRegEstaciones.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 250, -1));

        jlblNumTel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNumTel.setForeground(new java.awt.Color(0, 0, 0));
        jlblNumTel.setText("Número de Télefono:");
        jPnlInfoRegEstaciones.add(jlblNumTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 160, 20));

        jScrollPane10.setViewportView(jtxtNumTel);

        jPnlInfoRegEstaciones.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 250, -1));

        jlblHSalida1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblHSalida1.setForeground(new java.awt.Color(0, 0, 0));
        jlblHSalida1.setText("Correo:");
        jPnlInfoRegEstaciones.add(jlblHSalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 60, 20));

        jtxtCorreo.setText("trenesdobleA@gmail.com");
        jScrollPane11.setViewportView(jtxtCorreo);

        jPnlInfoRegEstaciones.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 250, -1));

        jScrollPane12.setViewportView(jtxtNombreEstacion);

        jPnlInfoRegEstaciones.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 250, -1));

        jlblNombreEstacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNombreEstacion.setForeground(new java.awt.Color(0, 0, 0));
        jlblNombreEstacion.setText("Nombre Estación:");
        jPnlInfoRegEstaciones.add(jlblNombreEstacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 29));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.weightx = 1.9;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(75, 18, 19, 0);
        jPanelFondo.add(jPnlInfoRegEstaciones, gridBagConstraints);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
       this.eliminarRegistro();
    }//GEN-LAST:event_jbtnEliminarDatosTrenActionPerformed

    private void jbtnModificarDatosTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarDatosTrenActionPerformed
        File ruta = new File(jtxtIDEstacion.getText());
        this.modificarRegistro(ruta);
       
    }//GEN-LAST:event_jbtnModificarDatosTrenActionPerformed

    private void jbtnGuardarDatosTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarDatosTrenActionPerformed
        File ruta = new File(jtxtIDEstacion.getText());
        this.AgregarRegistro(ruta);
    }//GEN-LAST:event_jbtnGuardarDatosTrenActionPerformed

    private void jBtnRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRecorridoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnRecorridoActionPerformed

    private void jBtnRegTrenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegTrenesActionPerformed
        registroTrenes Trenes = new registroTrenes();
        Trenes.setVisible(true);
        this.setVisible(false);
        moduloRegistro.registroTrenes.jlblTituloRegistroTrenes.setText("Registro de Trenes");
    }//GEN-LAST:event_jBtnRegTrenesActionPerformed

    private void jBtnRegEstacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegEstacionesActionPerformed
       this.setVisible(true);
       jlblTituloRegistroTrenes.setText("Registro de Estaciones");
    }//GEN-LAST:event_jBtnRegEstacionesActionPerformed

    private void jbtnLimpiarTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLimpiarTrenActionPerformed
        Limpiar_txt lp = new Limpiar_txt();
        lp.limpiar_texto(jPnlInfoRegEstaciones);
    }//GEN-LAST:event_jbtnLimpiarTrenActionPerformed

    private void jTableDatosEstacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDatosEstacionMouseClicked
        clic_tabla = jTableDatosEstacion.rowAtPoint(evt.getPoint());
        
        int ID = (int)jTableDatosEstacion.getValueAt(clic_tabla, 0);
        String nombre = ""+jTableDatosEstacion.getValueAt(clic_tabla, 1);
        String ubicacion = ""+jTableDatosEstacion.getValueAt(clic_tabla, 2);
        String HoraEntrada = ""+jTableDatosEstacion.getValueAt(clic_tabla, 3);
        String HoraSalida = ""+jTableDatosEstacion.getValueAt(clic_tabla, 4);
        String numTele = ""+jTableDatosEstacion.getValueAt(clic_tabla, 5);
        String Correo = ""+jTableDatosEstacion.getValueAt(clic_tabla, 6);
        
        jtxtIDEstacion.setText(String.valueOf(ID));
        jtxtNombreEstacion.setText(nombre);
        jtxtUbicacion.setText(String.valueOf(ubicacion));
        jtxtHEntrada.setText(String.valueOf(HoraEntrada));
        jtxtHSalida.setText(String.valueOf(HoraSalida));
        jtxtNumTel.setText(String.valueOf(numTele));
        jtxtCorreo.setText(String.valueOf(Correo));
        
        try{
//            JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 4);
//            lblFoto.setIcon(lbl.getIcon());
        }catch(Exception ex){
        }
    }//GEN-LAST:event_jTableDatosEstacionMouseClicked

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
            java.util.logging.Logger.getLogger(registroEstaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroEstaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroEstaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroEstaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new registroEstaciones().setVisible(true);
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
    private javax.swing.JPanel jPnlInfoRegEstaciones;
    private javax.swing.JPanel jPnlMenuRecorrido;
    private javax.swing.JPanel jPnlNavBar;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableDatosEstacion;
    private javax.swing.JButton jbtnEliminarDatosTren;
    private javax.swing.JButton jbtnGuardarDatosTren;
    private javax.swing.JButton jbtnLimpiarTren;
    private javax.swing.JButton jbtnMenuDesplegable;
    private javax.swing.JButton jbtnModificarDatosTren;
    private javax.swing.JLabel jlblHEntrada;
    private javax.swing.JLabel jlblHSalida;
    private javax.swing.JLabel jlblHSalida1;
    private javax.swing.JLabel jlblIDEstacion;
    private javax.swing.JLabel jlblNombreEstacion;
    private javax.swing.JLabel jlblNumTel;
    public static javax.swing.JLabel jlblTituloRegistroTrenes;
    private javax.swing.JLabel jlblUbicacion;
    private javax.swing.JTextPane jtxtCorreo;
    private javax.swing.JTextPane jtxtHEntrada;
    private javax.swing.JTextPane jtxtHSalida;
    private javax.swing.JTextPane jtxtIDEstacion;
    private javax.swing.JTextPane jtxtNombreEstacion;
    private javax.swing.JTextPane jtxtNumTel;
    private javax.swing.JTextPane jtxtUbicacion;
    // End of variables declaration//GEN-END:variables
}
