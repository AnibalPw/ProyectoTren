/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
/**
 *
 * @author Usuario
 */
// implements Serializable 

public class RegistroEstaciones{
    
    private int idEstacion;
    private String nombre;
    private String ubicacion;
    private String horaEntrada;
    private String horaSalida;
    private String numTelefono;
    private String correo;
    
    public RegistroEstaciones(){}
    
    public RegistroEstaciones(int pIdEstacion, String pNombre, String pUbicacion, String pHoEntrada, String pHoSalida, String pNumTel, String pCorreo)
    {
    this.idEstacion = pIdEstacion;
    this.nombre =  pNombre;
    this.ubicacion = pUbicacion;
    this.horaEntrada = pHoEntrada;
    this.horaSalida = pHoSalida;
    this.numTelefono = pNumTel;
    this.correo = pCorreo;
    }

    /**
     * @return the idEstacion
     */
    public int getIdEstacion() {
        return idEstacion;
    }

    /**
     * @param idEstacion the idEstacion to set
     */
    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the horaEntrada
     */
    public String getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * @param horaEntrada the horaEntrada to set
     */
    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     * @return the horaSalida
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida the horaSalida to set
     */
    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @return the numTelefono
     */
    public String getNumTelefono() {
        return numTelefono;
    }

    /**
     * @param numTelefono the numTelefono to set
     */
    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
