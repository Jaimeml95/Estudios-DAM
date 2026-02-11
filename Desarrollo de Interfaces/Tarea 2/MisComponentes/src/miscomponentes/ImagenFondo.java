/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miscomponentes;

import java.io.File;
import java.io.Serializable;

public class ImagenFondo implements Serializable {
    private File rutaInicial;
    private File rutaFinal;
    private float opacidadInicial;
    private float opacidadFinal;

    public ImagenFondo(File rutaInicial, File rutaFinal, float opacidadInicial, float opacidadFinal) {
        this.rutaInicial = rutaInicial;
        this.rutaFinal = rutaFinal;
        this.opacidadInicial = opacidadInicial;
        this.opacidadFinal = opacidadFinal;
    }

    public ImagenFondo() {}

    // --- Getters y Setters ---
    public File getRutaInicial() { return rutaInicial; }
    public void setRutaInicial(File rutaInicial) { this.rutaInicial = rutaInicial; }
    public File getRutaFinal() { return rutaFinal; }
    public void setRutaFinal(File rutaFinal) { this.rutaFinal = rutaFinal; }
    public float getOpacidadInicial() { return opacidadInicial; }
    public void setOpacidadInicial(float opacidadInicial) { this.opacidadInicial = opacidadInicial; }
    public float getOpacidadFinal() { return opacidadFinal; }
    public void setOpacidadFinal(float opacidadFinal) { this.opacidadFinal = opacidadFinal; }
}