/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea05equipoinformatico;

/**
 *
 * @author tpmvo
 */
public class ClasePrincipal {
    
        public static void main(String[] args) {

            java.util.Scanner entrada = new java.util.Scanner(System.in);
        //Creamos un objeto de GestionEquipos para poder usar sus metodos
        GestionEquipos g1 = new GestionEquipos();

        //Recogemos todo el codigo en try-catch para capturar las posibles excepciones que haya
        try {
            byte opcion;
            do {
                System.out.println("\nGESTOR DE EQUIPOS\n");
                System.out.println("Elige una opción.\n");
                System.out.println("Opción 1: Añadir equipo");
                System.out.println("Opción 2: Prestar equipo");
                System.out.println("Opción 3: Devolver equipo");
                System.out.println("Opción 4: Listar equipos");
                System.out.println("Opción 5: Salir\n");

                opcion = (Byte.parseByte(entrada.nextLine()));

                switch (opcion) {
                    case 1:
                        g1.aniadirEquipo();
                        break;
                    case 2:
                        g1.realizarPrestamo();
                        break;
                    case 3:
                        g1.realizarDevolucion();
                        break;
                    case 4:
                        g1.listarEquipos();
                        break;
                    case 5:
                        System.out.println("\nSaliendo del menú...");
                        break;
                    default:
                        System.out.println("\nError: introduce una opción válida.");
                }
            } while (opcion != 5);

        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce un numero valido");
        }
    }
}