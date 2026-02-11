/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea07_moro_lopez_jaime;

import java.util.Scanner;

/**
 *
 * @author tpmvo
 */
public class ClasePrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        GestionPaquetes gp = new GestionPaquetes();
        EmpresaReparto empresa = new EmpresaReparto();

        //Recogemos todo el codigo en try-catch para capturar las posibles excepciones que haya
        try {
            byte opcion;
            do {
                System.out.println("\nGESTION DE LOGÍSTICA\n");
                System.out.println("Elige una opción.\n");
                System.out.println("Opción 1: Añadir vehiculo");
                System.out.println("Opción 2: Listar vehiculos");
                System.out.println("Opción 3: Añadir paquete");
                System.out.println("Opción 4: Listar paquetes");
                System.out.println("Opción 5: Asignar paquete a vehículo");
                System.out.println("Opción 6: Lista de repartos");
                System.out.println("Opción 7: Devolver un paquete");
                System.out.println("Opción 8: Salir");

                opcion = (Byte.parseByte(entrada.nextLine()));

                switch (opcion) {
                    case 1:
                        gp.aniadirVehiculo(empresa, entrada);
                        break;
                    case 2:
                        gp.listarVehiculos();
                        break;
                    case 3:
                        gp.aniadirPaquete(empresa, entrada);
                        break;
                    case 4:
                        gp.listarPaquetes();
                        break;
                    case 5:
                        gp.asignarPaqueteAVehiculo();
                        break;
                    case 6:
                        gp.listaDeRepartos();
                        break;
                    case 7:
                        gp.devolverPaquete();
                        break;
                    case 8:
                        System.out.println("\nSaliendo del menú...");
                        break;
                    default:
                        System.out.println("\nError: introduce una opción válida.");
                }
            } while (opcion != 8);

        } catch (NumberFormatException e) {
            System.err.println("Introduce un numero valido");
        }
    }
}
