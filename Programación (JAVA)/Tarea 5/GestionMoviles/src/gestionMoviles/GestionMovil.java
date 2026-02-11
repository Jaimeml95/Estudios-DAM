/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionMoviles;

/**
 *
 * @author tpmvo
 */
import java.util.*;

public class GestionMovil {

    private static TreeSet<Movil> moviles = new TreeSet<>();
    private static LinkedHashMap<Integer, LinkedList<Integer>> llamadas = new LinkedHashMap<>();

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        //Recogemos todo el codigo en try-catch para capturar las posibles excepciones que haya
        try {
            byte opcion;
            do {
                System.out.println("\nGESTION DE MOVILES\n");
                System.out.println("Elige una opción.\n");
                System.out.println("Opción 1: Alta");
                System.out.println("Opción 2: Buscar");
                System.out.println("Opción 3: Baja");
                System.out.println("Opción 4: Modificar");
                System.out.println("Opción 5: Hacer llamada");
                System.out.println("Opción 6: Ver llamadas");
                System.out.println("Opción 7: Listado");
                System.out.println("Opción 8: Salir");

                opcion = (Byte.parseByte(entrada.nextLine()));

                switch (opcion) {
                    case 1:
                        añadirMovil(entrada);
                        break;
                    case 2:
                        buscarMovil(entrada);
                        break;
                    case 3:
                        eliminarMovil(entrada);
                        break;
                    case 4:
                        modificarMovil(entrada);
                        break;
                    case 5:
                        hacerLlamada(entrada);
                        break;
                    case 6:
                        verLlamadas(entrada);
                        break;
                    case 7:
                        listadoMoviles();
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

    private static void añadirMovil(Scanner entrada) {
        System.out.println("\nIntroduce los siguientes datos para registrar el movil");
        System.out.println("Numero de teléfono");
        int numTelefono = Integer.parseInt(entrada.nextLine());
        System.out.println("IMEI");
        long IMEI = Long.parseLong(entrada.nextLine());
        System.out.println("Modelo");
        String modelo = entrada.nextLine();
        Movil movil = new Movil(numTelefono, IMEI, modelo);
        if (moviles.add(movil)) {
            System.out.println("El movil fue agregado: " + movil);
        } else {
            System.out.println("El movil no se pudo agregar.");
        }
    }

    private static void buscarMovil(Scanner entrada) {
        System.out.print("Ingrese el número de teléfono a buscar: ");
        int numero = Integer.parseInt(entrada.nextLine());
        for (Movil movil : moviles) {
            if (movil.getNumTelefono() == numero) {
                System.out.println("Móvil encontrado: " + movil);
                return;
            }
        }
        System.out.println("No se encontró ningún móvil con ese número.");
    }

    private static void eliminarMovil(Scanner entrada) {
        System.out.print("Ingrese el número de teléfono a eliminar: ");
        int numero = Integer.parseInt(entrada.nextLine());

        boolean eliminado = false;
        Iterator<Movil> iterator = moviles.iterator();

        while (iterator.hasNext()) {
            Movil movil = iterator.next();
            if (movil.getNumTelefono() == numero) {
                iterator.remove();  // Elimina el móvil actual de la colección
                eliminado = true;   // Marca que se ha eliminado un móvil
                break;             // Salimos del bucle después de eliminar
            }
        }

        if (eliminado) {
            System.out.println("Móvil eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún móvil con ese número.");
        }
    }

    private static void modificarMovil(Scanner entrada) {
        System.out.print("Ingrese el número de teléfono: ");
        int numero = Integer.parseInt(entrada.nextLine());
        for (Movil movil : moviles) {
            if (movil.getNumTelefono() == numero) {
                System.out.print("Nuevo modelo: ");
                String nuevoModelo = entrada.nextLine();
                movil.setModelo(nuevoModelo);
                System.out.println("Modelo actualizado.");
                return;
            }
        }
        System.out.println("Móvil no encontrado.");
    }

    private static void llamarA(int numeroOrigen, int numeroDestino) {
        // Si no existe una lista de llamadas para el número origen, se crea una nueva lista
        if (!llamadas.containsKey(numeroOrigen)) {
            llamadas.put(numeroOrigen, new LinkedList<>());
        }

        // Se agrega el número destino a la lista de llamadas del número origen
        llamadas.get(numeroOrigen).addFirst(numeroDestino);
        System.out.println("Llamada registrada.");
    }

    private static void hacerLlamada(Scanner entrada) {
        System.out.print("Número de teléfono origen: ");
        int numeroOrigen = Integer.parseInt(entrada.nextLine());
        System.out.print("Número de teléfono destino: ");
        int numeroDestino = Integer.parseInt(entrada.nextLine());

        // Verificar que el número de origen existe
        boolean existe = false;
        for (Movil movil : moviles) {
            if (movil.getNumTelefono() == numeroOrigen) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            System.out.println("Error: Móvil de origen no registrado.");
            return;
        }

        llamarA(numeroOrigen, numeroDestino);
    }

    private static void verLlamadas(Scanner entrada) {
        System.out.print("Ingrese el número de teléfono: ");
        int numero = Integer.parseInt(entrada.nextLine());
        LinkedList<Integer> registros = llamadas.get(numero);

        if (registros == null || registros.isEmpty()) {
            System.out.println("No hay llamadas registradas para este número.");
        } else {
            System.out.println("Llamadas recientes:");
            for (int llamada : registros) {
                System.out.println("- " + llamada);
            }
        }
    }

    private static void listadoMoviles() {
        if (moviles.isEmpty()) {
            System.out.println("No hay móviles registrados.");
            return;
        }
        System.out.println("Listado de móviles (orden de llegada):");
        for (Movil movil : moviles) {
            System.out.println(movil);
        }
    }
}
