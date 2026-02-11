/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea05equipoinformatico;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tpmvo
 */
public class GestionEquipos {

    private Set<EquipoInformatico> Equipos = new HashSet<>();

    public GestionEquipos() {
    }

    @Override
    public String toString() {
        return "GestionEquipos{" + "Equipos=" + Equipos + '}';
    }

    public void aniadirEquipo() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\nIntroduce los siguientes datos para registrar el equipo");
        System.out.println("Id del equipo");
        String id = entrada.nextLine();
        System.out.println("Tipo de equipo");
        String tipo = entrada.nextLine();
        System.out.println("Se encuentra prestado (true/false)");
        boolean estado = Boolean.parseBoolean(entrada.nextLine());
        String prestadoA = "";
        if (estado) {
            System.out.println("Nombre de la persona a quien se ha prestado el equipo");
            prestadoA = entrada.nextLine();
        }
        EquipoInformatico equipo = new EquipoInformatico(id, tipo, estado, prestadoA);
        //Muestra un mensaje indicando si se ha podido añadir o no el equipo
        if (Equipos.add(equipo)) {
            System.out.println("El equipo fue agregado: " + equipo);
        } else {
            System.out.println("El equipo no se pudo agregar.");
        }
    }

    public void realizarPrestamo() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\nIntroduce los siguientes datos para realizar el prestamo");
        System.out.println("Id del equipo");
        String id = entrada.nextLine();
        System.out.println("Nombre del prestatario");
        String nombre = entrada.nextLine();
        for (EquipoInformatico equipo : Equipos) {
            if (equipo.getId().equalsIgnoreCase(id)) {
                equipo.prestarEquipo(nombre);
                break;
            }
            System.out.println("El equipo no existe");
        }
    }

    public void realizarDevolucion() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\nIntroduce los siguientes datos para realizar la devolucion");
        System.out.println("Id del equipo");
        String id = entrada.nextLine();
        for (EquipoInformatico equipo : Equipos) {
            if (equipo.getId().equalsIgnoreCase(id)) {
                equipo.devolverEquipo();
                break;
            }
            System.out.println("El equipo no existe");
        }
    }

    public void listarEquipos() {
        for (EquipoInformatico eq : Equipos) {
            System.out.println(eq.toString());
        }
    }
}
