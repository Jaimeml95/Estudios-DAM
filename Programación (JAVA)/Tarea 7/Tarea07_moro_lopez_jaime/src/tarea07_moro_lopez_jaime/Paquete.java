/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea07_moro_lopez_jaime;

/**
 *
 * @author tpmvo
 */
public class Paquete {

    private String codigo;
    private String direccionDestino;
    private short cpDestino;
    private boolean enReparto;
    private double volumen;

    public Paquete() {
    }

    public Paquete(String codigo, String direccionDestino, short cpDestino, boolean enReparto, double volumen) {
        this.codigo = codigo;
        this.direccionDestino = direccionDestino;
        this.cpDestino = cpDestino;
        this.enReparto = enReparto;
        this.volumen = volumen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public short getCpDestino() {
        return cpDestino;
    }

    public void setCpDestino(short cpDestino) {
        this.cpDestino = cpDestino;
    }

    public boolean isEnReparto() {
        return enReparto;
    }

    public void setEnReparto(boolean enReparto) {
        this.enReparto = enReparto;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    @Override
    public String toString() {
        return "Paquete{" + "codigo=" + codigo + ", direccionDestino=" + direccionDestino + ", cpDestino=" + cpDestino + ", enReparto=" + enReparto + ", volumen=" + volumen + '}';
    }

}
