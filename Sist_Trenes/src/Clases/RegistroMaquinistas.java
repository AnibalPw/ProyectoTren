/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class RegistroMaquinistas {
    
    private int idMaquinista;
    private String nombreMaquinista;
    private String recorrido;
    private String descripcion;

    public RegistroMaquinistas(){}
    
    public RegistroMaquinistas(int pIDMaquinista, String pNomMaquinista,String pRecorrido,String pDescripcion)
    {
        this.idMaquinista = pIDMaquinista;
        this.nombreMaquinista = pNomMaquinista;
        this.recorrido = pRecorrido;
        this.descripcion = pDescripcion;
    }

    /**
     * @return the idMaquinista
     */
    public int getIdMaquinista() {
        return idMaquinista;
    }

    /**
     * @param idMaquinista the idMaquinista to set
     */
    public void setIdMaquinista(int idMaquinista) {
        this.idMaquinista = idMaquinista;
    }

    /**
     * @return the nombreMaquinista
     */
    public String getNombreMaquinista() {
        return nombreMaquinista;
    }

    /**
     * @param nombreMaquinista the nombreMaquinista to set
     */
    public void setNombreMaquinista(String nombreMaquinista) {
        this.nombreMaquinista = nombreMaquinista;
    }

    /**
     * @return the recorrido
     */
    public String getRecorrido() {
        return recorrido;
    }

    /**
     * @param recorrido the recorrido to set
     */
    public void setRecorrido(String recorrido) {
        this.recorrido = recorrido;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
