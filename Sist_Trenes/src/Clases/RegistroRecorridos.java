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
public class RegistroRecorridos {
    
    private int IDRecorrido;
    private String nombreRuta;
    private double precioRecorrido;
    private String estacionSalida;
    private String estacionEntrada;
    
    public RegistroRecorridos(){}
    
    public RegistroRecorridos(int pIDRecorrido, String pNomRuta, double pPrecioRecorrido,String pEstacionS,String pEstacionE)
    {
        this.IDRecorrido = pIDRecorrido;
        this.nombreRuta = pNomRuta;
        this.precioRecorrido = pPrecioRecorrido;
        this.estacionSalida = pEstacionS;
        this.estacionEntrada = pEstacionE;
    }

    /**
     * @return the IDRecorrido
     */
    public int getIDRecorrido() {
        return IDRecorrido;
    }

    /**
     * @param IDRecorrido the IDRecorrido to set
     */
    public void setIDRecorrido(int IDRecorrido) {
        this.IDRecorrido = IDRecorrido;
    }

    /**
     * @return the precioRecorrido
     */
    public double getPrecioRecorrido() {
        return precioRecorrido;
    }

    /**
     * @param precioRecorrido the precioRecorrido to set
     */
    public void setPrecioRecorrido(double precioRecorrido) {
        this.precioRecorrido = precioRecorrido;
    }

    /**
     * @return the estacionSalida
     */
    public String getEstacionSalida() {
        return estacionSalida;
    }

    /**
     * @param estacionSalida the estacionSalida to set
     */
    public void setEstacionSalida(String estacionSalida) {
        this.estacionSalida = estacionSalida;
    }

    /**
     * @return the estacionEntrada
     */
    public String getEstacionEntrada() {
        return estacionEntrada;
    }

    /**
     * @param estacionEntrada the estacionEntrada to set
     */
    public void setEstacionEntrada(String estacionEntrada) {
        this.estacionEntrada = estacionEntrada;
    }

    /**
     * @return the nombreRuta
     */
    public String getNombreRuta() {
        return nombreRuta;
    }

    /**
     * @param nombreRuta the nombreRuta to set
     */
    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }
    
}
