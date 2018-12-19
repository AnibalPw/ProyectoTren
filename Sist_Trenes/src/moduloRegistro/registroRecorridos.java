/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moduloRegistro;
import Clases.Limpiar_txt;
import Clases.RegistroRecorridos;
import Clases.ProcRRecorridos;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static moduloRegistro.menuRegistro.jDdlDestino;
import moduloServicio.menuReserva;


/**
 *
 * @author Usuario
 */
public class registroRecorridos extends javax.swing.JFrame {

    
    Limpiar_txt lt = new Limpiar_txt();
    
    String barra = File.separator; //File.separator lo que hace es \\
    private String ruta_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\BDtxt\\RegistroRecorridos.txt";
    private String rutaIndividual_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\txtIndiviual\\Recorridos\\Recorridos";
    private String recorridos = System.getProperty("user.dir")+barra+"txtIndiviual"+barra+"Recorridos";
    
    File contenedor = new File(recorridos);
    
    File [] registros = contenedor.listFiles();
    
    RegistroRecorridos RegRecorridos;
    ProcRRecorridos PRRecorridos;
    
     int clic_tabla;
    registroEstaciones ddl = new registroEstaciones();
    
    public registroRecorridos() {
        initComponents();
       this.setLocationRelativeTo(null);
        PRRecorridos = new ProcRRecorridos();
        
        ddl.cargarDDLSalida();
        ddl.cargarDDLEntrada();
        
         try {
            cargar_txt();
            listarRegistro();
        } catch (Exception e) {
            mensaje("No existe el archivo txt");
        }
       
    }
    
   
        public void cargarDDLRecorrido()
    {
        for(int i = 0; i<registros.length; i++)
        {
            menuRegistro.jDdlDestino.addItem(registros[i].getName().replace(".recorridos", ""));
        }
    }
    
             public void cargarDDLRecorridoReserva()
    {
        for(int i = 0; i<registros.length; i++)
        {
            menuReserva.jDdlDestinoReserva.addItem(registros[i].getName().replace(".recorridos", ""));
        }
    }
    
    
    public void AgregarRegistro(File ruta){
        try{
            if(leerID() == -666)mensaje("Ingresar ID en número entero");
            else if(leerNRuta()== null)mensaje("Ingresar Nombre de la Ruta");
            else if(leerPrecio()== -666)mensaje("Ingresar Precio");
            else if(leerEstSalida()== null) mensaje("Elija estación Salida");
            else if(leerEstEntrada()== null)mensaje("Elija estación Entrada");
           
            else{
                RegRecorridos = new RegistroRecorridos(leerID(), leerNRuta(), leerPrecio(), leerEstSalida(), leerEstEntrada());
                if(PRRecorridos.BuscarID(RegRecorridos.getIDRecorrido())!= -1)mensaje("Este ID ya existe");
                else PRRecorridos.AgregarRecorrido(RegRecorridos);
                
                CrearTXTIndRecorrido();
                
                
                Guardar_txt();
                listarRegistro();
                lt.limpiar_texto(jPnlInfoRegRecorridos); 
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
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
                RegRecorridos = new RegistroRecorridos();
                RegRecorridos.setIDRecorrido(Integer.valueOf(St.nextToken()));
                RegRecorridos.setNombreRuta(St.nextToken());
                RegRecorridos.setPrecioRecorrido(Double.parseDouble(St.nextToken()));
                RegRecorridos.setEstacionSalida(St.nextToken());
                RegRecorridos.setEstacionEntrada(St.nextToken());
                PRRecorridos.AgregarRecorrido(RegRecorridos);
            }
            BR.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
    public void Guardar_txt(){
        String line = System.getProperty("line.separator"); 
        try{
            BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(ruta_txt)));
            for(int i = 0; i < PRRecorridos.cantidadRecorridos(); i++){
                RegRecorridos = PRRecorridos.ObtenerRecorridos(i);
                ficheroSalida.write(String.valueOf(RegRecorridos.getIDRecorrido()+"," +RegRecorridos.getNombreRuta()+", "+RegRecorridos.getPrecioRecorrido()+", "+RegRecorridos.getEstacionSalida()+",  "+RegRecorridos.getEstacionEntrada()));
                //ficheroSalida.write(String.valueOf(
                        //"ID="+RegRecorridos.getIDRecorrido()+","+"\r\n"+
                        //"Nombre Ruta="+RegRecorridos.getNombreRuta()+","+"\r\n"+
                        //"Precio Ruta="+RegRecorridos.getPrecioRecorrido()+","+"\r\n"+
                        //"Estación Salida="+RegRecorridos.getEstacionSalida()+","+"\r\n"+
                        //"Estación Entrada"+RegRecorridos.getEstacionEntrada()));
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
            File url = new File(rutaIndividual_txt + jtxtIDRuta.getText()+".recorridos");

             if(jtxtIDRuta.getText().equals(""))
           {
               JOptionPane.showMessageDialog(rootPane, "INIDIQUE EL REGISTRO");
           }
             else
             {
                if(dir.exists() && url.exists())
                {
                    if(leerID() == -666)mensaje("Ingresar ID en número entero");
                    else if(leerNRuta()== null)mensaje("Ingresar Nombre de la Ruta");
                    else if(leerPrecio()== -666)mensaje("Ingresar Precio");
                    else if(leerEstSalida()== null) mensaje("Elija estación Salida");
                    else if(leerEstEntrada()== null)mensaje("Elija estación Destino");
                    
                    else{
                        int ID = PRRecorridos.BuscarID(leerID());
                        RegRecorridos = new RegistroRecorridos(leerID(), leerNRuta(), leerPrecio(), leerEstSalida(), leerEstEntrada());
//                                "ID="+leerID()+"\r\n"+"",
//                                "Nombre Ruta="+leerNRuta()+"\r\n"+ "",
//                                "Precio Ruta="+leerPrecio()+"\r\n"+ "",
//                                "Estación Salida="+leerEstSalida()+"\r\n"+ "",
//                                "Estación Entrada="+leerEstEntrada());

                        if(ID == -1)PRRecorridos.AgregarRecorrido(RegRecorridos);
                        else PRRecorridos.ModificarRecorrido(ID, RegRecorridos);

                        EditarTXTIndRecorrido();

                        Guardar_txt();
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
    
    public void eliminarRegistro(){
        try{
            if(leerID() == -666) mensaje("Ingrese ID entero");
            
            else{
                int ID = PRRecorridos.BuscarID(leerID());
                if(ID == -1) mensaje("codigo no existe");
                
                else{
                    int si = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este la Estación","Si / No",0);
                    if(si == 0){
                        PRRecorridos.EliminarRecorrido(ID);
                        EliminarTXTIndRecorrido();
                        JOptionPane.showMessageDialog(rootPane, "Registro Eliminado");
                        
                        Guardar_txt();
                        listarRegistro();
                        lt.limpiar_texto(this.jPnlInfoRegRecorridos);
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
        dt.addColumn("Nombre Ruta");
        dt.addColumn("Precio Ruta");
        dt.addColumn("Estación Salida");
        dt.addColumn("Estación Entrada");
        //jTableDatosEstacion.setDefaultRenderer(Object.class, null);
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < PRRecorridos.cantidadRecorridos(); i++){
            RegRecorridos = PRRecorridos.ObtenerRecorridos(i);
            fila[0] = RegRecorridos.getIDRecorrido();
            fila[1] = RegRecorridos.getNombreRuta();
            fila[2] = RegRecorridos.getPrecioRecorrido();
            fila[3] = RegRecorridos.getEstacionSalida();
            fila[4] = RegRecorridos.getEstacionEntrada();
            dt.addRow(fila);
        }
        jTableDatosRuta.setModel(dt);
        jTableDatosRuta.setRowHeight(30);
    }
    
    public int leerID(){
        try{
            int ID = Integer.parseInt(jtxtIDRuta.getText().trim());
            return ID;
        }catch(Exception ex){
            return -666;
        }
    }
     
    public String leerNRuta(){
        try{
            String nombre = jtxtNomRuta.getText().trim().replace(" ", " ");
            return nombre;
        }catch(Exception ex){
            return null;
        }
    }
    
    public double leerPrecio(){
        try{
           double  precio = Double.parseDouble(jtxtPrecioRuta.getText().trim());
            return precio;
        }catch(Exception ex)
        {
            return -666;
        }
    }
    
    
    public String leerEstSalida(){
        try{
             //File url = new File(rutaIndividual_txt + jtxtIDRuta.getText()+".recorridos");
            //String EstSalida = jCbIDES.getItemAt(url.compareTo(url)).trim().replace(" ", " ");
            String EstSalida = (String)jCbIDES.getSelectedItem();
            return EstSalida;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerEstEntrada(){
        try{
            //File url = new File(rutaIndividual_txt + jtxtIDRuta.getText()+".recorridos");
           String EstEntrada = (String)jCbED.getSelectedItem();
            return EstEntrada;
        }catch(Exception ex)
        {
            return null;
        }
    }
   
    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
        
    
       
    private void CrearTXTIndRecorrido()
       {
           String archivo = jtxtIDRuta.getText()+".recorridos";
           //File crear_ubicacion = new File(rutaIndividual_txt);
           File crear_archivo = new File(rutaIndividual_txt + archivo);
           
           if(jtxtIDRuta.getText().equals(""))
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
                        crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s", 
                                "ID="+jtxtIDRuta.getText(),
                                "Nombre Ruta="+jtxtNomRuta.getText(), 
                                "Precio Ruta="+jtxtPrecioRuta.getText(), 
                                "Estación Salida="+jCbIDES.getItemAt(archivo.compareTo(archivo)), 
                                "Estación Entrada="+jCbED.getItemAt(archivo.compareTo(archivo)));
                        crear.close();
                    }
               } 
               catch (Exception e)
               {
                   
               }
           } 
       }
       
       private void EditarTXTIndRecorrido()
       {
           File url = new File(rutaIndividual_txt + jtxtIDRuta.getText()+".recorridos");
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
                     FileWriter escrito = new FileWriter(rutaIndividual_txt + jtxtIDRuta.getText()+".recorridos");
                     String ID = "ID=";
                     String NombreR = "Nombre Ruta=";
                     String PrecioR = "Precio Ruta=";
                     String EstSalida = "Estación Salida=";
                     String EstEntrda = "Estación Entrada=";
                     
                        PrintWriter guardar = new PrintWriter(escrito);
                        guardar.println(ID+jtxtIDRuta.getText());
                        guardar.println(NombreR+jtxtNomRuta.getText());
                        guardar.println(PrecioR+jtxtPrecioRuta.getText());
                        guardar.println(EstSalida+jCbIDES.getItemAt(archivo.compareTo(archivo)));
                        guardar.println(EstEntrda+jCbED.getItemAt(archivo.compareTo(archivo)));
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
       
       private void EliminarTXTIndRecorrido()
       {
            File url = new File(rutaIndividual_txt + jtxtIDRuta.getText()+".recorridos");
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
        jlblTituloRegistroTrenes = new javax.swing.JLabel();
        jbtnMenuDesplegable = new javax.swing.JButton();
        jPnlInfoRegRecorridos = new javax.swing.JPanel();
        jPanelOpciones = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableDatosRuta = new javax.swing.JTable();
        jlblPrecioRuta = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtxtPrecioRuta = new javax.swing.JTextPane();
        jlblEstacionS = new javax.swing.JLabel();
        jlblEstacionD = new javax.swing.JLabel();
        jbtnGuardarDatosTren = new javax.swing.JButton();
        jbtnModificarDatosTren = new javax.swing.JButton();
        jbtnEliminarDatosTren = new javax.swing.JButton();
        jbtnLimpiarTren = new javax.swing.JButton();
        jCbED = new javax.swing.JComboBox<>();
        jCbIDES = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtxtNomRuta = new javax.swing.JTextPane();
        jlblNomRuta = new javax.swing.JLabel();
        jlblNomRuta1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtxtIDRuta = new javax.swing.JTextPane();
        jPnlMenuRecorrido = new javax.swing.JPanel();
        jBtnHome = new javax.swing.JButton();
        jBtnReportes = new javax.swing.JButton();
        jBtnReservarEspacio = new javax.swing.JButton();
        jBtnConsulta = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jBtnRegRuta = new javax.swing.JButton();
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
        jPnlNavBar.add(jlblTituloRegistroTrenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 250, 30));

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

        jTableDatosRuta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Tren", "Precio ruta", "Estación Salida", "Estación Entrada"
            }
        ));
        jTableDatosRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDatosRutaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableDatosRuta);

        jPanelOpciones.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 220));

        jPnlInfoRegRecorridos.add(jPanelOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 800, 220));

        jlblPrecioRuta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblPrecioRuta.setForeground(new java.awt.Color(0, 0, 0));
        jlblPrecioRuta.setText("Precio Ruta:");
        jPnlInfoRegRecorridos.add(jlblPrecioRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 90, 29));

        jScrollPane3.setViewportView(jtxtPrecioRuta);

        jPnlInfoRegRecorridos.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 230, -1));

        jlblEstacionS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblEstacionS.setForeground(new java.awt.Color(0, 0, 0));
        jlblEstacionS.setText("Estación Salida:");
        jPnlInfoRegRecorridos.add(jlblEstacionS, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 120, 29));

        jlblEstacionD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblEstacionD.setForeground(new java.awt.Color(0, 0, 0));
        jlblEstacionD.setText("Estación Destino");
        jPnlInfoRegRecorridos.add(jlblEstacionD, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, 29));

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
        jPnlInfoRegRecorridos.add(jbtnLimpiarTren, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 40, -1));

        jPnlInfoRegRecorridos.add(jCbED, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 230, 30));

        jCbIDES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbIDESActionPerformed(evt);
            }
        });
        jPnlInfoRegRecorridos.add(jCbIDES, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 230, 30));

        jScrollPane4.setViewportView(jtxtNomRuta);

        jPnlInfoRegRecorridos.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 230, -1));

        jlblNomRuta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNomRuta.setForeground(new java.awt.Color(0, 0, 0));
        jlblNomRuta.setText("Nombre de ruta: ");
        jPnlInfoRegRecorridos.add(jlblNomRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 140, 29));

        jlblNomRuta1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNomRuta1.setForeground(new java.awt.Color(0, 0, 0));
        jlblNomRuta1.setText("ID de ruta: ");
        jPnlInfoRegRecorridos.add(jlblNomRuta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 140, 29));

        jScrollPane6.setViewportView(jtxtIDRuta);

        jPnlInfoRegRecorridos.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 230, -1));

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

        jBtnRegRuta.setBackground(new java.awt.Color(204, 204, 204));
        jBtnRegRuta.setForeground(new java.awt.Color(91, 91, 91));
        jBtnRegRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Tracks_26px.png"))); // NOI18N
        jBtnRegRuta.setText("Registrar Recorrido");
        jBtnRegRuta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRegRuta.setFocusable(false);
        jBtnRegRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegRutaActionPerformed(evt);
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
            .addComponent(jBtnRegRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jBtnRegRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jBtnRegRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegRutaActionPerformed
        this.setVisible(true);
        jlblTituloRegistroTrenes.setText("Registro de Recorridos");
    }//GEN-LAST:event_jBtnRegRutaActionPerformed

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
         File ruta = new File(jtxtIDRuta.getText());
        this.modificarRegistro(ruta);
    }//GEN-LAST:event_jbtnModificarDatosTrenActionPerformed

    private void jbtnGuardarDatosTrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarDatosTrenActionPerformed
          File ruta = new File(jtxtIDRuta.getText());
        this.AgregarRegistro(ruta);
    }//GEN-LAST:event_jbtnGuardarDatosTrenActionPerformed

    private void jBtnRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRecorridoActionPerformed
        this.setVisible(true);
        jlblTituloRegistroTrenes.setText("Registro de Recorridos");
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

    private void jCbIDESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbIDESActionPerformed
        String copiar = (String) jCbIDES.getSelectedItem();
         jCbIDES.setName(String.valueOf(jCbIDES.getSelectedObjects()));
    }//GEN-LAST:event_jCbIDESActionPerformed

    private void jTableDatosRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDatosRutaMouseClicked
        clic_tabla = jTableDatosRuta.rowAtPoint(evt.getPoint());
        
        int ID = (int)jTableDatosRuta.getValueAt(clic_tabla, 0);
        String nombreRuta = ""+jTableDatosRuta.getValueAt(clic_tabla, 1);
        double precio = (double)jTableDatosRuta.getValueAt(clic_tabla, 2);
        String EstSalida = ""+jTableDatosRuta.getValueAt(clic_tabla, 3);
        String EstEntrada = ""+jTableDatosRuta.getValueAt(clic_tabla, 4);
        
        jtxtIDRuta.setText(String.valueOf(ID));
        jtxtNomRuta.setText(nombreRuta);
        jtxtPrecioRuta.setText(String.valueOf(precio));
        jCbIDES.setName(String.valueOf(EstSalida));
        jCbED.setName(String.valueOf(EstEntrada));
        
        try{
//            JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 4);
//            lblFoto.setIcon(lbl.getIcon());
        }catch(Exception ex){
        }
    }//GEN-LAST:event_jTableDatosRutaMouseClicked

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
            java.util.logging.Logger.getLogger(registroRecorridos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroRecorridos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroRecorridos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroRecorridos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registroRecorridos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConsulta;
    private javax.swing.JButton jBtnHome;
    private javax.swing.JButton jBtnRecorrido;
    private javax.swing.JButton jBtnRegEstaciones;
    private javax.swing.JButton jBtnRegFuncionario;
    private javax.swing.JButton jBtnRegRuta;
    private javax.swing.JButton jBtnRegTrenes;
    private javax.swing.JButton jBtnReportes;
    private javax.swing.JButton jBtnReservarEspacio;
    public static javax.swing.JComboBox<String> jCbED;
    public static javax.swing.JComboBox<String> jCbIDES;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPnlInfoRegRecorridos;
    private javax.swing.JPanel jPnlMenuRecorrido;
    private javax.swing.JPanel jPnlNavBar;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableDatosRuta;
    private javax.swing.JButton jbtnEliminarDatosTren;
    private javax.swing.JButton jbtnGuardarDatosTren;
    private javax.swing.JButton jbtnLimpiarTren;
    private javax.swing.JButton jbtnMenuDesplegable;
    private javax.swing.JButton jbtnModificarDatosTren;
    private javax.swing.JLabel jlblEstacionD;
    private javax.swing.JLabel jlblEstacionS;
    private javax.swing.JLabel jlblNomRuta;
    private javax.swing.JLabel jlblNomRuta1;
    private javax.swing.JLabel jlblPrecioRuta;
    public static javax.swing.JLabel jlblTituloRegistroTrenes;
    public static javax.swing.JTextPane jtxtIDRuta;
    private javax.swing.JTextPane jtxtNomRuta;
    private javax.swing.JTextPane jtxtPrecioRuta;
    // End of variables declaration//GEN-END:variables
}
