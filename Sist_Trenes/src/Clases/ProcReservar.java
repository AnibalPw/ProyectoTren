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
public class ProcReservar {
    
    ArrayList<Object> lista = new ArrayList<Object>();
    
    public ProcReservar(){}
    
    public ProcReservar(ArrayList<Object> Lista)
    {
    this.lista = Lista;
    }
    
    public void AgregaeCliente(Reservar nombreC)
      {
        this.lista.add(nombreC);
      }
    
      public void ModificarCliente(int IDC, Reservar nombreC)
      {
          this.lista.set(IDC, nombreC);
      }
      
      public void EliminarCliente(int IDC)
      {
          this.lista.remove(IDC);
      }
      
      public Reservar ObtenerCliente(int IDC)
      {
          return(Reservar)lista.get(IDC);
      }
      
      public int cantidadCliente()
      {
        return this.lista.size();
      }
      
      public int BuscarIDCliente(int IDC)
      {
        for(int i = 0; i < cantidadCliente(); i++){
            if(IDC == ObtenerCliente(i).getIdCliente())return i;
        }
        return -1;
      }
    
    
}
