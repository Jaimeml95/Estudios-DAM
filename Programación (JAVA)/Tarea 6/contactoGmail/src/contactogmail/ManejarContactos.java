/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactogmail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author tpmvo
 */
public class ManejarContactos {

    Set<Contacto> contactos = new TreeSet<>();

    public void aniadirContacto() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\nAñadiendo contacto nuevo");
        System.out.println("Introduce los siguientes datos para añadir el contacto\n");
        System.out.println("Nombre");
        String nombre = entrada.nextLine();
        System.out.println("Apellidos");
        String apellidos = entrada.nextLine();
        System.out.println("Email");
        String email = entrada.nextLine();

        Contacto contacto = new Contacto(nombre, apellidos, email);
        //Muestra un mensaje indicando si se ha podido añadir o no el equipo
        if (contactos.add(contacto)) {
            System.out.println("\nEl contacto fue agregado: " + contacto);
        } else {
            System.out.println("El contacto no se pudo agregar.");
        }
    }

    public void ordenarContactos(Set<Contacto> contactosOriginales) {
        List<Contacto> listaOrdenada = new ArrayList<>(contactosOriginales);
        listaOrdenada.sort(Comparator.comparing(Contacto::getApellidos, String.CASE_INSENSITIVE_ORDER));
        // "Crea un Comparator que compare objetos Contacto usando el método getApellidos()."
        // El operador :: se llama operador de referencia a método (method reference).
        // Sirve para referenciar un método existente de una clase o un objeto sin llamarlo directamente.

        /* OPCION MAS SIMPLE
        listaOrdenada.sort(new Comparator<Contacto>() {
        @Override
        public int compare(Contacto c1, Contacto c2) {
        return c1.getApellidos().compareToIgnoreCase(c2.getApellidos());
        }
        });
         */
        System.out.println("\nContactos ordenados por apellidos:");
        for (Contacto c : listaOrdenada) {
            System.out.println(c.toString());
        }
    }

    public void mostrarContactos() {
        System.out.println("\nMostrando contactos (ordenados por email)");
        for (Contacto c : contactos) {
            System.out.println(c.toString());
        }
    }
}
