/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ProcRTrenes {
    
    ArrayList<Object> lista = new ArrayList<Object>();
    
    public ProcRTrenes(){}
    
    public ProcRTrenes(ArrayList<Object> Lista)
    {
    this.lista = Lista;
    }
    
    public void AgregarTren(RegistroTrenes capacidad)
      {
        this.lista.add(capacidad);
      }
    
      public void ModificarTren(int IDT, RegistroTrenes capacidad)
      {
          this.lista.set(IDT, capacidad);
      }
      
      public void EliminarTren(int IDT)
      {
          this.lista.remove(IDT);
      }
      
      public RegistroTrenes ObtenerTren(int IDT)
      {
          return(RegistroTrenes)lista.get(IDT);
      }
      
      public int cantidadTren()
      {
        return this.lista.size();
      }
      
      public int BuscarID(int IDT)
      {
        for(int i = 0; i < cantidadTren(); i++){
            if(IDT == ObtenerTren(i).getIDTrenes())return i;
        }
        return -1;
      }
    
    
}
