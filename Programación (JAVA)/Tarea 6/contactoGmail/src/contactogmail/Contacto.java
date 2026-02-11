/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactogmail;

/**
 *
 * @author tpmvo
 */
public class Contacto implements Comparable<Contacto> {

    private String nombre;
    private String apellidos;
    private String email;

    public Contacto() {
    }

    public Contacto(String nombre, String apellidos, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + '}';
    }

    @Override
    public int compareTo(Contacto otro) {
        return this.email.compareToIgnoreCase(otro.email);
    }

}
