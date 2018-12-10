/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carga;

import javax.swing.JProgressBar;

/**
 *
 * @author Usuario
 */
public class CargarBarra extends Thread{
    
    JProgressBar progreso;
    public CargarBarra (JProgressBar progreso){
        super();
        this.progreso = progreso;
    }
    @Override
    public void run(){
    
        for(int i=1; i<=100; i++){
            PreCarga.lblCargar.setText("Cargando " + i + " " );
            progreso.setValue(i);
            
        pause(50);
        }
    }
    public void pause(int milSeg){
        try{
         Thread.sleep(milSeg);
        }
        catch(Exception ex){
        
        }
   
    }
    
}
