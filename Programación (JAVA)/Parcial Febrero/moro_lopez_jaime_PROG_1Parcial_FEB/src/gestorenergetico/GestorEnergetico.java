/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorenergetico;

import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class GestorEnergetico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        //Creamos un objeto de GestorDispositivo para poder usar sus metodos
        GestorDispositivo g1 = new GestorDispositivo();

        //Recogemos todo el codigo en try-catch para capturar las posibles excepciones que haya
        try {
            byte opcion;
            do {
                System.out.println("\nGESTOR DE DISPOSITIVOS\n");
                System.out.println("Elige una opción.\n");
                System.out.println("Opción 1: Crear dispositivo");
                System.out.println("Opción 2: Encender dispositivo");
                System.out.println("Opción 3: Apagar dispositivo");
                System.out.println("Opción 4: Calcular consumo actual");
                System.out.println("Opción 5: Listar dispositivos");
                System.out.println("Opción 6: Salir");

                opcion = (Byte.parseByte(entrada.nextLine()));

                switch (opcion) {
                    case 1:
                        g1.agregarDispositivo();
                        break;
                    case 2:
                        g1.encenderDispositivo();
                        break;
                    case 3:
                        g1.apagarDispositivo();
                        break;
                    case 4:
                        System.out.println("El consumo total es " + g1.calcularConsumo() + " vatios");
                        break;
                    case 5:
                        g1.listarDispositivos();
                        break;
                    case 6:
                        System.out.println("\nSaliendo del menú...");
                        break;
                    default:
                        System.out.println("\nError: introduce una opción válida.");
                }
            } while (opcion != 6);

        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce un numero valido");
        }
    }
}
