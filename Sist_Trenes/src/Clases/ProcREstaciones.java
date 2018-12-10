/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Usuario
 */
public class ProcREstaciones {
    
    ArrayList<Object> lista = new ArrayList<Object>();

    public ProcREstaciones(){}
    
    public ProcREstaciones(ArrayList<Object> lista)
    {
    this.lista = lista;
    }
    
      public void agregarRegistro(RegistroEstaciones nombre){
        this.lista.add(nombre);
    }
    
      public void modificarRegistro (int ID, RegistroEstaciones nombre)
      {
          this.lista.set(ID, nombre);
      }
      
      public void EliminarRegistro (int ID)
      {
          this.lista.remove(ID);
      }
      
      public RegistroEstaciones ObtenerRegistro(int ID)
      {
          return(RegistroEstaciones)lista.get(ID);
      }
      
      public int cantidadRegistro(){
        return this.lista.size();
    }
      
      public int BuscarID(int ID){
        for(int i = 0; i < cantidadRegistro(); i++){
            if(ID == ObtenerRegistro(i).getIdEstacion())return i;
        }
        return -1;
    }
    
}
