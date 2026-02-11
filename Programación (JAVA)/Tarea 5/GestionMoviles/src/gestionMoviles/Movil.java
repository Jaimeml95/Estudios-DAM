package gestionMoviles;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tpmvo
 */
public class Movil implements Comparable<Movil> {

    //Atributos
    private int numTelefono;
    private long IMEI;
    private String modelo;

    //Metodos
    public Movil() {
    }

    public Movil(int numTelefono, long IMEI, String modelo) {
        this.numTelefono = numTelefono;
        this.IMEI = IMEI;
        this.modelo = modelo;
    }

    public int getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(int numTelefono) {
        this.numTelefono = numTelefono;
    }

    public long getIMEI() {
        return IMEI;
    }

    public void setIMEI(long IMEI) {
        this.IMEI = IMEI;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Movil{" + "numTelefono=" + numTelefono + ", IMEI=" + IMEI + ", modelo=" + modelo + '}';
    }

    @Override
    public int compareTo(Movil otro) {
        return Integer.compare(this.numTelefono, (otro.numTelefono));
    }
}
