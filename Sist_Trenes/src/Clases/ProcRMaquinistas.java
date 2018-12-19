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
public class ProcRMaquinistas {
    
     ArrayList<Object> lista = new ArrayList<Object>();
    
    public ProcRMaquinistas(){}
    
    public ProcRMaquinistas(ArrayList<Object> Lista)
    {
    this.lista = Lista;
    }
    
    public void AgregarMaquinista(RegistroMaquinistas nombre)
      {
        this.lista.add(nombre);
      }
    
      public void ModificarMaquinista(int IDM, RegistroMaquinistas nombre)
      {
          this.lista.set(IDM, nombre);
      }
      
      public void EliminarMaquinista(int IDM)
      {
          this.lista.remove(IDM);
      }
      
      public RegistroMaquinistas ObtenerMaquinista(int IDM)
      {
          return(RegistroMaquinistas)lista.get(IDM);
      }
      
      public int cantidadMaquinistas()
      {
        return this.lista.size();
      }
      
      public int BuscarID(int IDM)
      {
        for(int i = 0; i < cantidadMaquinistas(); i++){
            if(IDM == ObtenerMaquinista(i).getIdMaquinista())return i;
        }
        return -1;
      }
    
}
