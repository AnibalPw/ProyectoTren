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
public class ProcRRecorridos {

    ArrayList<Object> lista = new ArrayList<Object>();
    
    public ProcRRecorridos(){}
    
    public ProcRRecorridos(ArrayList<Object> Lista)
    {
    this.lista = Lista;
    }
    
    public void AgregarRecorrido(RegistroRecorridos nombre)
      {
        this.lista.add(nombre);
      }
    
      public void ModificarRecorrido(int IDR, RegistroRecorridos nombre)
      {
          this.lista.set(IDR, nombre);
      }
      
      public void EliminarRecorrido(int IDR)
      {
          this.lista.remove(IDR);
      }
      
      public RegistroRecorridos ObtenerRecorridos(int IDR)
      {
          return(RegistroRecorridos)lista.get(IDR);
      }
      
      public int cantidadRecorridos()
      {
        return this.lista.size();
      }
      
      public int BuscarID(int IDR)
      {
        for(int i = 0; i < cantidadRecorridos(); i++){
            if(IDR == ObtenerRecorridos(i).getIDRecorrido())return i;
        }
        return -1;
      }
}
