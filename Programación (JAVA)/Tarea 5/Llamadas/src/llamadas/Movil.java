package llamadas;

import java.util.LinkedList;

        
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tpmvo
 */

public class Movil{

    //Atributos
    private int numTelefono;
    private long IMEI;
    private String modelo;
    private LinkedList <Integer> llamadas;

    //Metodos

    public Movil() {
    }

    public Movil(int numTelefono, long IMEI, String modelo) {
        this.numTelefono = numTelefono;
        this.IMEI = IMEI;
        this.modelo = modelo;
        this.llamadas = new LinkedList<>();
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

    public LinkedList<Integer> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(LinkedList<Integer> llamadas) {
        this.llamadas = llamadas;
    }

    @Override
    public String toString() {
        return "Movil{" + "numTelefono=" + numTelefono + ", IMEI=" + IMEI + ", modelo=" + modelo + ", llamadas=" + llamadas + '}';
    }
    
public void llamarA(int numero) {
        llamadas.addFirst(numero);
    }
    
}
