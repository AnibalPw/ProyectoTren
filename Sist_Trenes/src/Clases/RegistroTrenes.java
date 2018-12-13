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
public class RegistroTrenes {
    
    private int IDTrenes;
    private int VagonesTren;
    private double CapacidadTren;
    
    public RegistroTrenes(){}
    
     public RegistroTrenes(int pIdTrenes, int pVagTren, double pCapaTren)
    {
    this.IDTrenes = pIdTrenes;
    this.VagonesTren = pVagTren;
    this.CapacidadTren = pCapaTren;
   
    }

    /**
     * @return the IDTrenes
     */
    public int getIDTrenes() {
        return IDTrenes;
    }

    /**
     * @param IDTrenes the IDTrenes to set
     */
    public void setIDTrenes(int IDTrenes) {
        this.IDTrenes = IDTrenes;
    }

    /**
     * @return the VagonesTren
     */
    public int getVagonesTren() {
        return VagonesTren;
    }

    /**
     * @param VagonesTren the VagonesTren to set
     */
    public void setVagonesTren(int VagonesTren) {
        this.VagonesTren = VagonesTren;
    }

    /**
     * @return the CapacidadTren
     */
    public double getCapacidadTren() {
        return CapacidadTren;
    }

    /**
     * @param CapacidadTren the CapacidadTren to set
     */
    public void setCapacidadTren(double CapacidadTren) {
        this.CapacidadTren = CapacidadTren;
    }

      
}
