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
public class Reservar {
    
    private String recorrido;
    private double precioViaje;
    private String fecha;
    private String codigoViaje;
    private int idTren;
    private String nombEstacion;
    private int idCliente;
    private String nombreCliente;
    private int cantPasajeros;
    private double montoCobro;
    private double pagoCliente;
    private double cambioCliente;
    
    
    public Reservar(){}
    
    public Reservar(String pRecorrido, double pPrecioV, String pFecha, String pCodigoV, int pIdTren, String pNomEstacion, int pidCliente, String pNombCliente, int pCantPasajero, double pMonCobro, double pPagoC, double pCambioC)
    {
    this.recorrido = pRecorrido; 
    this.precioViaje =  pPrecioV;
    this.fecha = pFecha;
    this.codigoViaje = pCodigoV;
    this.idTren = pIdTren;
    this.nombEstacion = pNomEstacion;
    this.idCliente = pidCliente;
    this.nombreCliente= pNombCliente;
    this.cantPasajeros = pCantPasajero;
    this.montoCobro= pMonCobro;
    this.pagoCliente= pPagoC;
    this.cambioCliente= pCambioC;
    
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
     * @return the precioViaje
     */
    public double getPrecioViaje() {
        return precioViaje;
    }

    /**
     * @param precioViaje the precioViaje to set
     */
    public void setPrecioViaje(double precioViaje) {
        this.precioViaje = precioViaje;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the codigoViaje
     */
    public String getCodigoViaje() {
        return codigoViaje;
    }

    /**
     * @param codigoViaje the codigoViaje to set
     */
    public void setCodigoViaje(String codigoViaje) {
        this.codigoViaje = codigoViaje;
    }

    /**
     * @return the idTren
     */
    public int getIdTren() {
        return idTren;
    }

    /**
     * @param idTren the idTren to set
     */
    public void setIdTren(int idTren) {
        this.idTren = idTren;
    }


    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the cantPasajeros
     */
    public int getCantPasajeros() {
        return cantPasajeros;
    }

    /**
     * @param cantPasajeros the cantPasajeros to set
     */
    public void setCantPasajeros(int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
    }

    /**
     * @return the montoCobro
     */
    public double getMontoCobro() {
        return montoCobro;
    }

    /**
     * @param montoCobro the montoCobro to set
     */
    public void setMontoCobro(double montoCobro) {
        this.montoCobro = montoCobro;
    }

    /**
     * @return the pagoCliente
     */
    public double getPagoCliente() {
        return pagoCliente;
    }

    /**
     * @param pagoCliente the pagoCliente to set
     */
    public void setPagoCliente(double pagoCliente) {
        this.pagoCliente = pagoCliente;
    }

    /**
     * @return the cambioCliente
     */
    public double getCambioCliente() {
        return cambioCliente;
    }

    /**
     * @param cambioCliente the cambioCliente to set
     */
    public void setCambioCliente(double cambioCliente) {
        this.cambioCliente = cambioCliente;
    }

    /**
     * @return the nombEstacion
     */
    public String getNombEstacion() {
        return nombEstacion;
    }

    /**
     * @param nombEstacion the nombEstacion to set
     */
    public void setNombEstacion(String nombEstacion) {
        this.nombEstacion = nombEstacion;
    }
    
    
}
