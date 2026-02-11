/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea05equipoinformatico;

import java.util.Objects;

/**
 *
 * @author tpmvo
 */
public class EquipoInformatico {

    private String id;
    private String tipo;
    private boolean estado;
    private String prestadoA;

    public EquipoInformatico() {
    }

    public EquipoInformatico(String id, String tipo, boolean estado, String prestadoA) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.prestadoA = prestadoA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getPrestadoA() {
        return prestadoA;
    }

    public void setPrestadoA(String prestadoA) {
        this.prestadoA = prestadoA;
    }

    @Override
    public String toString() {
        return "EquipoInformatico{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", prestadoA=" + prestadoA + '}';
    }

    public void prestarEquipo(String prestatario) {
        if (!this.estado) {
            this.prestadoA = prestatario;
            this.estado = true;
            System.out.println("El equipo se ha prestado correctamente");
        } else {
            System.out.println("El equipo no se encuentra disponible");
        }
    }

    public void devolverEquipo() {
        if (this.estado) {
            this.estado = false;
            this.prestadoA = "";
            System.out.println("El equipo se ha devuelto correctamente");
        } else {
            System.out.println("El equipo se encontraba disponible");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;                        // Mismo objeto exacto
        }
        if (obj == null || getClass() != obj.getClass()) // Null o tipo diferente
        {
            return false;
        }

        EquipoInformatico otro = (EquipoInformatico) obj;    // Casteo
        return id.equals(otro.id);                           // Comparación por ID
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
