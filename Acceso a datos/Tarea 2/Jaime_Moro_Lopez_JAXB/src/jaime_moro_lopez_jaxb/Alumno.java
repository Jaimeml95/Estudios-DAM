/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaime_moro_lopez_jaxb;

/**
 *
 * @author tpmvo
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// Esta anotación dice que esta clase corresponde a la etiqueta <Alumno>
@XmlRootElement(name = "Alumno")
// Define el orden en el que aparecerán los elementos (opcional)
@XmlType(propOrder = {"apellido", "edad", "nota"})
public class Alumno {
    private String apellido;
    private int edad;
    private double nota;

    public Alumno() {} // Constructor vacío obligatorio para JAXB

    public Alumno(String apellido, int edad, double nota) {
        this.apellido = apellido;
        this.edad = edad;
        this.nota = nota;
    }

    @XmlElement(name = "Apellido")
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    @XmlElement(name = "Edad")
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    @XmlElement(name = "Nota")
    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }
}
