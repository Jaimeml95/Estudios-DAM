/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaime_moro_lopez_tarea2;

/**
 *
 * @author tpmvo
 */
import java.io.File;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        File directorio = new File("Tarea2");
        try {
            // Crear directorio si no existe
            if (!directorio.exists()) {
                directorio.mkdir();
                System.out.println("Directorio Tarea2 creado.");
            }

            File fichero = new File(directorio, "prueba.txt");
            if (fichero.exists()) {
                System.out.println("El fichero ya existe.");
            } else {
                if (fichero.createNewFile()) {
                    System.out.println("El fichero ha sido creado correctamente.");
                }
            }

            // Listar contenido del directorio actual
            System.out.println("\nContenido del directorio actual:");
            File actual = new File(".");
            String[] lista = actual.list();
            for (String nombre : lista) {
                System.out.println(nombre);
            }

            // Información del fichero
            System.out.println("\nInformación del fichero:");
            System.out.println("Nombre: " + fichero.getName());
            System.out.println("Ruta: " + fichero.getPath());
            System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
            System.out.println("Longitud: " + fichero.length() + " bytes");

        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } finally {
            // Operaciones de limpieza si fueran necesarias
        }
    }
}