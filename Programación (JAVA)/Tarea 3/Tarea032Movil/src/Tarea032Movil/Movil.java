package Tarea032Movil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tpmvo
 */
public class Movil {
    
    //Atributos
    
    private int numTelefono;
    private long IMEI;
    private String marca;
    private String modelo;
    private So SistemaOperativo;
    private Cam camaraFrontal;
    private Cam camaraTrasera;
    
    //Metodos

    //Constructores
    
    public Movil() {
    }

    public Movil(int numTelefono, long IMEI, String marca, String modelo, So SistemaOperativo, Cam camaraFrontal, Cam camaraTrasera) {
        this.numTelefono = numTelefono;
        this.IMEI = IMEI;
        this.marca = marca;
        this.modelo = modelo;
        this.SistemaOperativo = SistemaOperativo;
        this.camaraFrontal = camaraFrontal;
        this.camaraTrasera = camaraTrasera;
    }

    //Getters y Setters
    
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public So getSistemaOperativo() {
        return SistemaOperativo;
    }

    public void setSistemaOperativo(So SistemaOperativo) {
        this.SistemaOperativo = SistemaOperativo;
    }

    public Cam getCamaraFrontal() {
        return camaraFrontal;
    }

    public void setCamaraFrontal(Cam camaraFrontal) {
        this.camaraFrontal = camaraFrontal;
    }

    public Cam getCamaraTrasera() {
        return camaraTrasera;
    }

    public void setCamaraTrasera(Cam camaraTrasera) {
        this.camaraTrasera = camaraTrasera;
    }
    
    //toString

    /*
    toString generado por la funcion de insertar codigo
    @Override
    public String toString() {
        return "Movil{" + "numTelefono=" + numTelefono + ", IMEI=" + IMEI + ", marca=" + marca + ", modelo=" + modelo + ", SistemaOperativo=" + SistemaOperativo + ", camaraFrontal=" + camaraFrontal + ", camaraTrasera=" + camaraTrasera + '}';
    }
    */
    
    //toString modificado
    @Override
    public String toString() {
        return "\nMovil\n\n" +
               "Número de Teléfono: " + numTelefono + "\n" +
               "IMEI: " + IMEI + "\n" +
               "Marca: " + marca + "\n" +
               "Modelo: " + modelo + "\n" +
               "Sistema Operativo: " + SistemaOperativo + "\n" +
               "Cámara Frontal: " + camaraFrontal + "\n" +
               "Cámara Trasera: " + camaraTrasera;
    }
    
}
