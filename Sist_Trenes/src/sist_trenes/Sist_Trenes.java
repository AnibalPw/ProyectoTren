/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sist_trenes;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Usuario
 */
public class Sist_Trenes {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        IntMenu inicio = new IntMenu();
        inicio.setVisible(true);
     }
    
     public static String fechaActual()
        {
            
            Date fecha =  new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY h:mm a");
            
            return formatoFecha.format(fecha);
        }
     

}
