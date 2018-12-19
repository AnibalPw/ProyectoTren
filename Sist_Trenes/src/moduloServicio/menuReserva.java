/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloServicio;

import Clases.Limpiar_txt;
import Clases.ProcRMaquinistas;
import java.io.File;
import Clases.Reservar;
import Clases.ProcReservar;
import Clases.RegistroMaquinistas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import moduloRegistro.registroRecorridos;
import static moduloRegistro.registroRecorridos.jtxtIDRuta;


/**
 *
 * @author Usuario
 */
public class menuReserva extends javax.swing.JFrame {

    
    Limpiar_txt lt = new Limpiar_txt();
    
    String barra = File.separator; //File.separator lo que hace es \\
    private String ruta_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\BDtxt\\RegistroRecorridos.txt";
    private String rutaIndividual_txt = "C:\\Users\\Usuario\\Desktop\\Universidad\\Programación\\Estructura de Datos\\Sistema de Trenes\\Sist_Trenes\\txtIndiviual\\Recorridos\\Recorridos";

    
    Reservar ReservaC;
    ProcReservar PReservaC;
    
    int clic_tabla;
    registroRecorridos ddl = new registroRecorridos();
    
    
    public menuReserva() {
        initComponents();
        this.setLocationRelativeTo(null);
       //jlblFecha.setText(Sist_Trenes.fechaActual());
       jlblFecha.setText(sist_trenes.Sist_Trenes.fechaActual());
       
         PReservaC = new ProcReservar();
        
         ddl.cargarDDLRecorridoReserva();
        
         try {
            cargar_txt();
            listarRegistro();
        } catch (Exception e) {
            mensaje("No existe el archivo txt");
        }
       
        
    }
        private int aumento = 0;
       
 
        public void AgregarCliente(File ruta){
        try{
            if(leerRecorrido()== null)mensaje("Seleccione el Recorrido");
            else if(leerPrecioUni()== -666)mensaje(" ");
            else if(leerFecha()== null)mensaje(" ");
            else if(leerCodigoV()== null) mensaje("Digite Codigo de Viaje");
            else if(leerIDTren()== -666) mensaje("Digite ID Tren");
            else if(leerNEstacion()== null) mensaje("Digite Nombre Estación");
            else if(leerIDCliente()== -666) mensaje("Digite ID Cliente");
            else if(leerNCliente()== null) mensaje("Digite Nombre Cliente");
            else if(leerCantPasa()== -666) mensaje("Seleccione la Cantidad de Pasajeros");
            else if(leerMonCobro()== -666) mensaje(" ");
            else if(leerMonPago()== -666) mensaje(" ");
            else if(leerCambio()== -666) mensaje(" ");
            
            else{
                ReservaC = new Reservar(leerRecorrido(), leerPrecioUni(), leerFecha(), leerCodigoV(), leerIDTren(), leerNEstacion(), leerIDCliente(), leerNCliente(), leerCantPasa(), leerMonCobro(), leerMonPago(), leerCambio());
                if(PReservaC.BuscarIDCliente(ReservaC.getIdCliente())!= -1)mensaje("Este ID ya existe");
                else PReservaC.AgregaeCliente(ReservaC);
                
                CrearTXTIndClientes();
                
                
                Guardar_txt();
                listarRegistro();
                lt.limpiar_texto(jPanelPago); 
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
     
         
        public void Guardar_txt(){
        String line = System.getProperty("line.separator"); 
        try{
            BufferedWriter ficheroSalida = new BufferedWriter(new FileWriter(new File(ruta_txt)));
            for(int i = 0; i < PReservaC.cantidadCliente(); i++){
                ReservaC = PReservaC.ObtenerCliente(i);
                ficheroSalida.write(String.valueOf(ReservaC.getRecorrido()+"," +ReservaC.getPrecioViaje()+", "+ReservaC.getFecha()+", "+ReservaC.getCodigoViaje()+","+ ReservaC.getIdTren()+","+ReservaC.getNombEstacion()+","+ReservaC.getIdCliente()+","+ ReservaC.getNombreCliente()+","+ReservaC.getMontoCobro()+","+ReservaC.getPagoCliente()+","+ReservaC.getCambioCliente()));
              
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
                ReservaC = new Reservar();
                ReservaC.setRecorrido(String.valueOf(St.nextToken()));
                ReservaC.setPrecioViaje(Double.parseDouble(St.nextToken()));
                ReservaC.setFecha(St.nextToken());
                ReservaC.setCodigoViaje(String.valueOf(St.nextToken()));
                ReservaC.setIdTren(Integer.parseInt(St.nextToken()));
                ReservaC.setNombEstacion(String.valueOf(St.nextToken()));
                ReservaC.setIdCliente(Integer.parseInt(St.nextToken()));
                ReservaC.setNombreCliente(String.valueOf(St.nextToken()));
                ReservaC.setRecorrido(St.nextToken());
                ReservaC.setCantPasajeros(Integer.parseInt(St.nextToken()));
                ReservaC.setMontoCobro(Double.parseDouble(St.nextToken()));
                ReservaC.setPagoCliente(Double.parseDouble(St.nextToken()));
                ReservaC.setCambioCliente(Double.parseDouble(St.nextToken()));
                
                PReservaC.AgregaeCliente(ReservaC);
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
            File url = new File(rutaIndividual_txt + jtxtIDCliente.getText()+".clientes");

             if(jtxtIDRuta.getText().equals(""))
           {
               JOptionPane.showMessageDialog(rootPane, "INIDIQUE EL REGISTRO");
           }
             else
             {
                if(dir.exists() && url.exists())
                {
                    if(leerRecorrido()== null)mensaje("Seleccione el Recorrido");
                    else if(leerPrecioUni()== -666)mensaje(" ");
                    else if(leerFecha()== null)mensaje(" ");
                    else if(leerCodigoV()== null) mensaje("Digite Codigo de Viaje");
                    else if(leerIDTren()== -666) mensaje("Digite ID Tren");
                    else if(leerNEstacion()== null) mensaje("Digite Nombre Estación");
                    else if(leerIDCliente()== -666) mensaje("Digite ID Cliente");
                    else if(leerNCliente()== null) mensaje("Digite Nombre Cliente");
                    else if(leerCantPasa()== -666) mensaje("Seleccione la Cantidad de Pasajeros");
                    else if(leerMonCobro()== -666) mensaje(" ");
                    else if(leerMonPago()== -666) mensaje(" ");
                    else if(leerCambio()== -666) mensaje(" ");
                    
                    else{
                        int ID = PReservaC.BuscarIDCliente(leerIDCliente());
                        ReservaC = new Reservar(leerRecorrido(), leerPrecioUni(), leerFecha(), leerCodigoV(), leerIDTren(), leerNEstacion(), leerIDCliente(), leerNCliente(), leerCantPasa(), leerMonCobro(),leerMonPago(),leerCambio());

                        if(ID == -1)PReservaC.AgregaeCliente(ReservaC);
                        else PReservaC.ModificarCliente(ID, ReservaC);

                        EditarTXTIndClientes();

                        Guardar_txt();
                        listarRegistro();
                        lt.limpiar_texto(jPanelPago);
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
            if(leerIDCliente()== -666) mensaje("Ingrese ID entero");
            
            else{
                int ID = PReservaC.BuscarIDCliente(leerIDCliente());
                if(ID == -1) mensaje("codigo no existe");
                
                else{
                    int si = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este la Estación","Si / No",0);
                    if(si == 0){
                        PReservaC.EliminarCliente(ID);
                        EliminarTXTIndClientes();
                        JOptionPane.showMessageDialog(rootPane, "Registro Eliminado");
                        
                        Guardar_txt();
                        //listarRegistro();
                        lt.limpiar_texto(this.jPanelPago);
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
        
        dt.addColumn("Recorrido");
        dt.addColumn("Precio Unitario");
        dt.addColumn("Fecha");
        dt.addColumn("Codigo Viaje");
        dt.addColumn("ID Tren");
        dt.addColumn("Nombre Estacion");
        dt.addColumn("ID Cliente");
        dt.addColumn("Nombre Cliente");
        dt.addColumn("Cantidad Pasajeros");
        dt.addColumn("Monto Cobro");
        dt.addColumn("Pago del Cliente");
        dt.addColumn("Cambio a Cliente");
        //jTableDatosEstacion.setDefaultRenderer(Object.class, null);
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < PReservaC.cantidadCliente(); i++){
            ReservaC = PReservaC.ObtenerCliente(i);
            fila[0] = ReservaC.getRecorrido();
            fila[1] = ReservaC.getPrecioViaje();
            fila[2] = ReservaC.getFecha();
            fila[3] = ReservaC.getCodigoViaje();
            fila[4] = ReservaC.getIdTren();
            fila[5] = ReservaC.getNombEstacion();
            fila[6] = ReservaC.getIdCliente();
            fila[7] = ReservaC.getNombreCliente();
            fila[8] = ReservaC.getCantPasajeros();
            fila[9] = ReservaC.getMontoCobro();
            fila[10] = ReservaC.getPagoCliente();
            fila[11] = ReservaC.getCambioCliente();
            dt.addRow(fila);
        }
        //jTableDatosMaquinista.setModel(dt);
        //jTableDatosMaquinista.setRowHeight(30);
    }
         
         
    public String leerRecorrido(){
        try{
            String recorrido = (String)jDdlDestinoReserva.getSelectedItem();
            return recorrido;
        }catch(Exception ex){
            return null;
        }
    }     
        
    public double leerPrecioUni(){
        try{
           double  precioUni = Double.parseDouble(jlblPrecioUni.getText().trim());
            return precioUni;
        }catch(Exception ex)
        {
            return -666;
        }
    }
     
      public String leerFecha(){
        try{
            String FechaV = jlblFecha.getText().trim().replace(" ", " ");
            return FechaV;
        }catch(Exception ex){
            return null;
        }
    }
       public String leerCodigoV(){
        try{
            String codigoV = jtxtCodigoViaje.getText().trim().replace(" ", " ");
            return codigoV;
        }catch(Exception ex){
            return null;
        }
    }
    
    public int leerIDTren(){
        try{
            int IDT = Integer.parseInt(jtxtIDTren.getText().trim());
            return IDT;
        }catch(Exception ex){
            return -666;
        }
    }
     
    
    public String leerNEstacion(){
        try{
            String nombreE = jtxtNomEstacion.getText().trim().replace(" ", " ");
            return nombreE;
        }catch(Exception ex){
            return null;
        }
    }
    
    public int leerIDCliente(){
        try{
            int IDC = Integer.parseInt(jtxtIDCliente.getText().trim());
            return IDC;
        }catch(Exception ex){
            return -666;
        }
    }
    
    
    public String leerNCliente(){
        try{
            String nomCliente = jtxtNombreCliente.getText().trim().replace(" ", " ");
            return nomCliente;
        }catch(Exception ex){
            return null;
        }
    }
    
    
    
    public int leerCantPasa(){
        try{
            int cantPasajeros = Integer.parseInt(jlblContadorPasajeros.getText().trim());
            return cantPasajeros;
        }catch(Exception ex){
            return -666;
        }
    }
   
    
    public double leerMonCobro(){
        try{
           double  montoCobro = Double.parseDouble(jtxtTotalPagar.getText().trim());
            return montoCobro;
        }catch(Exception ex)
        {
            return -666;
        }
    }
    
    
    public double leerMonPago(){
        try{
           double  montoPago = Double.parseDouble(jtxtPagoCliente.getText().trim());
            return montoPago;
        }catch(Exception ex)
        {
            return -666;
        }
    }
    
     public double leerCambio(){
        try{
           double montoCambio = Double.parseDouble(jtxtCambioaCliente.getText().trim());
            return montoCambio;
        }catch(Exception ex)
        {
            return -666;
        }
    }
    
    
    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
        
     private void CrearTXTIndClientes()
   {
           String archivo = jtxtIDCliente.getText()+".clientes";
           File crear_archivo = new File(rutaIndividual_txt + archivo);
           
           if(jtxtIDCliente.getText().equals(""))
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
                        crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s", 
                                "Recorrido= "+jDdlDestinoReserva.getItemAt(archivo.compareTo(archivo)), 
                                "Precio Unitario="+jlblPrecioUni.getText(), 
                                "Fecha= "+jlblFecha.getText(), 
                                "Codigo Viaje= "+jtxtCodigoViaje.getText(),
                                "ID Tren= "+jtxtIDTren.getText(), 
                                "Nombre Estación= "+jtxtNomEstacion.getText(), 
                                "ID Cliente= "+jtxtIDCliente.getText(), 
                                "Nombre Cliente= "+jtxtNombreCliente.getText(),
                                "Cantidad Pasajeros= "+jlblContadorPasajeros.getText(), 
                                "Monto Cobro= "+jtxtTotalPagar.getText(), 
                                "Monto Pago= "+jtxtPagoCliente.getText(),
                                "Monto Cambio= "+jtxtCambioaCliente.getText());
                        crear.close();
                    }
               } 
               catch (Exception e)
               {
                   
               }
           } 
       }
     
     private void EditarTXTIndClientes()
       {
           File url = new File(rutaIndividual_txt + jtxtIDCliente.getText()+".clientes");
           String archivo = jtxtIDCliente.getText()+".clientes";
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
                     String Recorrido = "Recorrido=";
                     String PrecioUni = "Precio Unitario=";
                     String fecha = "Fecha=";
                     String CViaje = "Codigo Viaje=";
                     String IDTren = "ID Tren=";
                     String  NomEstacion= "Nombre Estación= ";
                     String IDCliente = "ID Cliente= ";
                     String NomCliente= "Nombre Cliente= ";
                     String CantPasajeros = "Cantidad Pasajeros= ";
                     String MCobro = "Monto a Cobra= ";
                     String MPago = "Pago Clinete= ";
                     String Cambio = "Cambio a Cliente= ";
                     
                        PrintWriter guardar = new PrintWriter(escrito);
                        guardar.println(Recorrido+jDdlDestinoReserva.getItemAt(archivo.compareTo(archivo)));
                        guardar.println(PrecioUni+jlblPrecioUni.getText());
                        guardar.println(fecha+jlblFecha.getText());
                        guardar.println(CViaje+jtxtCodigoViaje.getText());
                        guardar.println(IDTren+jtxtIDTren.getText());
                        guardar.println(NomEstacion+jtxtNomEstacion.getText());
                        guardar.println(IDCliente+jtxtIDCliente.getText());
                        guardar.println(NomCliente+jtxtNombreCliente.getText());
                        guardar.println(CantPasajeros+jlblContadorPasajeros.getText());
                        guardar.println(MCobro+jtxtTotalPagar.getText());
                        guardar.println(MPago+jtxtPagoCliente.getText());
                        guardar.println(Cambio+jtxtCambioaCliente.getText());
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
    
     private void EliminarTXTIndClientes()
       {
            File url = new File(rutaIndividual_txt + jtxtIDCliente.getText()+".clientes");
            if(jtxtIDCliente.getText().equals(""))
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
        jlblTitulo = new javax.swing.JLabel();
        jbtnMenuDesplegable = new javax.swing.JButton();
        jPanelPago = new javax.swing.JPanel();
        jPanelOpciones = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jlblPrecio = new javax.swing.JLabel();
        jlblUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtNombreCliente = new javax.swing.JTextPane();
        jlblFecha = new javax.swing.JLabel();
        jlblDestino = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtxtCodigoViaje = new javax.swing.JTextPane();
        jlblContadorPasajeros = new javax.swing.JLabel();
        jlblViaje = new javax.swing.JLabel();
        jlblNumPasajeroTitulo = new javax.swing.JLabel();
        jbtnMas = new javax.swing.JButton();
        jbtnMenos = new javax.swing.JButton();
        jlblMontoTotal = new javax.swing.JLabel();
        jtxtTotalPagar = new javax.swing.JTextField();
        jDdlDestinoReserva = new javax.swing.JComboBox<>();
        jlblFechaHora = new javax.swing.JLabel();
        jlblPrecioUni = new javax.swing.JLabel();
        jtxtPagoCliente = new javax.swing.JTextField();
        jlblMontoTotal1 = new javax.swing.JLabel();
        jtxtCambioaCliente = new javax.swing.JTextField();
        jlblMontoTotal2 = new javax.swing.JLabel();
        jlblViaje1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtxtIDTren = new javax.swing.JTextPane();
        jlblNomEstacion = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtxtNomEstacion = new javax.swing.JTextPane();
        jlblDCliente = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtxtIDCliente = new javax.swing.JTextPane();
        jbtnPago = new javax.swing.JButton();
        jPnlEligeCampo = new javax.swing.JPanel();
        jPanelMPrecios = new javax.swing.JPanel();
        jlblTituloTarifas = new javax.swing.JLabel();
        jlblRutaCSJ = new javax.swing.JLabel();
        jBtnHome = new javax.swing.JButton();
        jBtnReportes = new javax.swing.JButton();
        jBtnRegistro = new javax.swing.JButton();
        jBtnConsulta = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jBtnRecorrido = new javax.swing.JButton();

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
        jPnlNavBar.add(jlblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 250, -1));

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

        jPanelPago.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPago.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelOpciones.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelOpciones.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jPanelOpciones.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 650, 10));

        jlblPrecio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlblPrecio.setForeground(new java.awt.Color(102, 102, 102));
        jlblPrecio.setText("Precio por Ticket");
        jPanelOpciones.add(jlblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 80, 29));

        jlblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jlblUsuario.setText("Nombre del cliente:");
        jPanelOpciones.add(jlblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, 29));

        jScrollPane1.setViewportView(jtxtNombreCliente);

        jPanelOpciones.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 180, -1));

        jlblFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblFecha.setForeground(new java.awt.Color(0, 0, 0));
        jlblFecha.setText("dd/mm/yyyy");
        jPanelOpciones.add(jlblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 170, 20));

        jlblDestino.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblDestino.setForeground(new java.awt.Color(0, 0, 0));
        jlblDestino.setText("Destino:");
        jPanelOpciones.add(jlblDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 29));

        jScrollPane3.setViewportView(jtxtCodigoViaje);

        jPanelOpciones.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 100, -1));

        jlblContadorPasajeros.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblContadorPasajeros.setForeground(new java.awt.Color(0, 0, 0));
        jlblContadorPasajeros.setText("0");
        jlblContadorPasajeros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelOpciones.add(jlblContadorPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 20, -1));

        jlblViaje.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblViaje.setForeground(new java.awt.Color(0, 0, 0));
        jlblViaje.setText("Código del Viaje:");
        jPanelOpciones.add(jlblViaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 29));

        jlblNumPasajeroTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNumPasajeroTitulo.setForeground(new java.awt.Color(0, 0, 0));
        jlblNumPasajeroTitulo.setText("Número de Pasajeros:");
        jPanelOpciones.add(jlblNumPasajeroTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 160, 20));

        jbtnMas.setBackground(new java.awt.Color(255, 255, 255));
        jbtnMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plus_26px.png"))); // NOI18N
        jbtnMas.setToolTipText("Aumenta Ticket");
        jbtnMas.setBorder(null);
        jbtnMas.setContentAreaFilled(false);
        jbtnMas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnMas.setFocusable(false);
        jbtnMas.setOpaque(true);
        jbtnMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMasActionPerformed(evt);
            }
        });
        jPanelOpciones.add(jbtnMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 40, 30));

        jbtnMenos.setBackground(new java.awt.Color(255, 255, 255));
        jbtnMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Minus_26px.png"))); // NOI18N
        jbtnMenos.setToolTipText("Disminuye ticket");
        jbtnMenos.setBorder(null);
        jbtnMenos.setBorderPainted(false);
        jbtnMenos.setContentAreaFilled(false);
        jbtnMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnMenos.setFocusable(false);
        jbtnMenos.setOpaque(true);
        jbtnMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMenosActionPerformed(evt);
            }
        });
        jPanelOpciones.add(jbtnMenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 40, 30));

        jlblMontoTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblMontoTotal.setForeground(new java.awt.Color(0, 0, 0));
        jlblMontoTotal.setText("Monto a Pagar:");
        jPanelOpciones.add(jlblMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 110, 30));

        jtxtTotalPagar.setEnabled(false);
        jPanelOpciones.add(jtxtTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 139, 30));

        jDdlDestinoReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDdlDestinoReservaActionPerformed(evt);
            }
        });
        jPanelOpciones.add(jDdlDestinoReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 220, -1));

        jlblFechaHora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlblFechaHora.setForeground(new java.awt.Color(102, 102, 102));
        jlblFechaHora.setText("Fecha y hora");
        jPanelOpciones.add(jlblFechaHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 70, 29));

        jlblPrecioUni.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblPrecioUni.setForeground(new java.awt.Color(0, 0, 0));
        jlblPrecioUni.setText("₡");
        jPanelOpciones.add(jlblPrecioUni, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 110, 20));

        jtxtPagoCliente.setEnabled(false);
        jPanelOpciones.add(jtxtPagoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 139, 30));

        jlblMontoTotal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblMontoTotal1.setForeground(new java.awt.Color(0, 0, 0));
        jlblMontoTotal1.setText("Paga con:");
        jPanelOpciones.add(jlblMontoTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 80, 30));

        jtxtCambioaCliente.setEnabled(false);
        jPanelOpciones.add(jtxtCambioaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 139, 30));

        jlblMontoTotal2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblMontoTotal2.setForeground(new java.awt.Color(0, 0, 0));
        jlblMontoTotal2.setText("Cambio:");
        jPanelOpciones.add(jlblMontoTotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 60, 30));

        jlblViaje1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblViaje1.setForeground(new java.awt.Color(0, 0, 0));
        jlblViaje1.setText("ID Tren:");
        jPanelOpciones.add(jlblViaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, 29));

        jScrollPane4.setViewportView(jtxtIDTren);

        jPanelOpciones.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 100, -1));

        jlblNomEstacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblNomEstacion.setForeground(new java.awt.Color(0, 0, 0));
        jlblNomEstacion.setText("Nombre Estación:");
        jPanelOpciones.add(jlblNomEstacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, -1, 29));

        jScrollPane5.setViewportView(jtxtNomEstacion);

        jPanelOpciones.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 110, -1));

        jlblDCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblDCliente.setForeground(new java.awt.Color(0, 0, 0));
        jlblDCliente.setText("ID Cliente:");
        jPanelOpciones.add(jlblDCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 29));

        jScrollPane6.setViewportView(jtxtIDCliente);

        jPanelOpciones.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 100, -1));

        jbtnPago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/colon-costarricense.png"))); // NOI18N
        jbtnPago.setText("Guardar y Cobrar");
        jbtnPago.setFocusable(false);
        jbtnPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPagoActionPerformed(evt);
            }
        });
        jPanelOpciones.add(jbtnPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, 170, -1));

        jPanelPago.add(jPanelOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 670, 450));

        jPnlEligeCampo.setBackground(new java.awt.Color(51, 51, 51));
        jPnlEligeCampo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPnlEligeCampo.setForeground(new java.awt.Color(255, 255, 255));
        jPnlEligeCampo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelPago.add(jPnlEligeCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 450, 450));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.weightx = 1.9;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(34, 18, 19, 15);
        jPanelFondo.add(jPanelPago, gridBagConstraints);

        jlblTituloTarifas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblTituloTarifas.setForeground(new java.awt.Color(0, 0, 0));
        jlblTituloTarifas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Train_26px.png"))); // NOI18N
        jlblTituloTarifas.setText("Tarifa de Trenes");

        jlblRutaCSJ.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jlblRutaCSJ.setForeground(new java.awt.Color(91, 91, 91));
        jlblRutaCSJ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Train_26px.png"))); // NOI18N
        jlblRutaCSJ.setText("Cartago-San José : ₡1000");

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

        javax.swing.GroupLayout jPanelMPreciosLayout = new javax.swing.GroupLayout(jPanelMPrecios);
        jPanelMPrecios.setLayout(jPanelMPreciosLayout);
        jPanelMPreciosLayout.setHorizontalGroup(
            jPanelMPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblTituloTarifas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlblRutaCSJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnReportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtnConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addComponent(jBtnRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMPreciosLayout.setVerticalGroup(
            jPanelMPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMPreciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jBtnRegistro)
                .addGap(18, 18, 18)
                .addComponent(jBtnRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jBtnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblTituloTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlblRutaCSJ, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1448, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnMenuDesplegableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMenuDesplegableActionPerformed

        int posicion = this.jPanelMPrecios.getX();
        if(posicion > -1)
        {
        Animacion.Animacion.mover_izquierda(0, -185, 2, 2, jPanelMPrecios);
        }
        else
        {
        Animacion.Animacion.mover_derecha(-185, 12, 2, 2, jPanelMPrecios);
        } 
    }//GEN-LAST:event_jbtnMenuDesplegableActionPerformed

    private void jbtnPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPagoActionPerformed
        File ruta = new File(jtxtIDCliente.getText());
        this.AgregarCliente(ruta);
    }//GEN-LAST:event_jbtnPagoActionPerformed

    private void jDdlDestinoReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDdlDestinoReservaActionPerformed
         String copiar = (String) jDdlDestinoReserva.getSelectedItem();
         jDdlDestinoReserva.setName(String.valueOf(jDdlDestinoReserva.getSelectedObjects()));
         
    }//GEN-LAST:event_jDdlDestinoReservaActionPerformed

    private void jBtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistroActionPerformed
    
        moduloRegistro.menuRegistro Registro = new  moduloRegistro.menuRegistro();
        Registro.setVisible(true);
        this.setVisible(false);
        moduloRegistro.menuRegistro.jlblTituloRegistro.setText("Registro de Funcionarios");
        
    }//GEN-LAST:event_jBtnRegistroActionPerformed

    private void jBtnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConsultaActionPerformed
        moduloServicio.menuConsulta Consulta = new moduloServicio.menuConsulta();
        Consulta.setVisible(true);
        this.setVisible(false);
        moduloServicio.menuConsulta.jlblTituloConsulta.setText("Consulta Información");
    }//GEN-LAST:event_jBtnConsultaActionPerformed

    private void jBtnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHomeActionPerformed
        sist_trenes.IntMenu Inicio = new sist_trenes.IntMenu();
        Inicio.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jBtnHomeActionPerformed

    private void jbtnMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMasActionPerformed
        //String temp = "";
        if(jbtnMas != jbtnMenos){
            aumento++;
            String contador = String.valueOf(aumento);
            jlblContadorPasajeros.setText(contador);
            //temp = contador;
            }
    }//GEN-LAST:event_jbtnMasActionPerformed

    private void jbtnMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMenosActionPerformed
        if(jbtnMenos != jbtnMas ){
            if(aumento<=0){
            JOptionPane.showMessageDialog(null, "Imposible disminuir la cantidad");
            }
            else{
            aumento--;
            String contador = String.valueOf(aumento);
            jlblContadorPasajeros.setText(contador);
            //temp = contador;
            }
           
            }
        
    }//GEN-LAST:event_jbtnMenosActionPerformed

    private void jBtnRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRecorridoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnRecorridoActionPerformed

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
    private javax.swing.JButton jBtnConsulta;
    private javax.swing.JButton jBtnHome;
    private javax.swing.JButton jBtnRecorrido;
    private javax.swing.JButton jBtnRegistro;
    private javax.swing.JButton jBtnReportes;
    public static javax.swing.JComboBox<String> jDdlDestinoReserva;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelMPrecios;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelPago;
    private javax.swing.JPanel jPnlEligeCampo;
    private javax.swing.JPanel jPnlNavBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnMas;
    private javax.swing.JButton jbtnMenos;
    private javax.swing.JButton jbtnMenuDesplegable;
    private javax.swing.JButton jbtnPago;
    public static javax.swing.JLabel jlblContadorPasajeros;
    private javax.swing.JLabel jlblDCliente;
    private javax.swing.JLabel jlblDestino;
    private javax.swing.JLabel jlblFecha;
    private javax.swing.JLabel jlblFechaHora;
    private javax.swing.JLabel jlblMontoTotal;
    private javax.swing.JLabel jlblMontoTotal1;
    private javax.swing.JLabel jlblMontoTotal2;
    private javax.swing.JLabel jlblNomEstacion;
    private javax.swing.JLabel jlblNumPasajeroTitulo;
    private javax.swing.JLabel jlblPrecio;
    private javax.swing.JLabel jlblPrecioUni;
    private javax.swing.JLabel jlblRutaCSJ;
    private javax.swing.JLabel jlblTitulo;
    private javax.swing.JLabel jlblTituloTarifas;
    private javax.swing.JLabel jlblUsuario;
    private javax.swing.JLabel jlblViaje;
    private javax.swing.JLabel jlblViaje1;
    private javax.swing.JTextField jtxtCambioaCliente;
    private javax.swing.JTextPane jtxtCodigoViaje;
    private javax.swing.JTextPane jtxtIDCliente;
    private javax.swing.JTextPane jtxtIDTren;
    private javax.swing.JTextPane jtxtNomEstacion;
    private javax.swing.JTextPane jtxtNombreCliente;
    private javax.swing.JTextField jtxtPagoCliente;
    private javax.swing.JTextField jtxtTotalPagar;
    // End of variables declaration//GEN-END:variables
}
