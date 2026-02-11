/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorenergetico;

import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class GestorDispositivo {

//Declaracion de Atributos
    private Set<Dispositivo> dispositivos = new HashSet<>();
    //Utilizaremos HashSet porque es el mas indicado si lo que queremos es que no existan duplicados,
    //sin saber el numero de elemenos de antemano, el orden no es importante y la tarea principal
    //es la busqueda
    private final int potencia = 4600;

    //Metodos
    //Agrega un nuevo dispositivo
    public void agregarDispositivo() {

        Scanner entrada = new Scanner(System.in);

        System.out.println("\nIntroduce los siguientes datos para registrar el dispositivo");
        System.out.println("Nombre del dispositivo");
        String nombre = entrada.nextLine();
        System.out.println("Año de fabricacion");
        int anioFabricacion = Integer.parseInt(entrada.nextLine());
        System.out.println("Consumo inicial");
        int consumoInicial = Integer.parseInt(entrada.nextLine());
        System.out.println("Se encuentra encendido (true/false)");
        boolean funcionando = Boolean.parseBoolean(entrada.nextLine());
        Dispositivo dispositivo = new Dispositivo(nombre, anioFabricacion, consumoInicial, funcionando);
        //Muestra un mensaje indicando si se ha podido o no agregar el dispositivo
        if (dispositivos.add(dispositivo)) {
            System.out.println("El dispositivo fue agregado: " + dispositivo);
        } else {
            System.out.println("El dispositivo no se pudo agregar.");
        }
    }

    //Enciende un dispositivo (en el caso de que esté apagado y no supere la potencia contratada)
    public void encenderDispositivo() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el nombre del dispositivo: ");
        String nombre = entrada.nextLine();
        for (Dispositivo dispositivo : dispositivos) {
            if (dispositivo.getNombre().equalsIgnoreCase(nombre)) {
                if (dispositivo.isFuncionando()) {
                    System.out.println("El dispositivo ya esta encendido");
                    return;
                }
                if (calcularConsumo() < potencia) {
                    dispositivo.setFuncionando(true);
                    System.out.println("El dispositivo se ha encendido.");
                    return;
                } else {
                    System.out.println("Error: El dispositivo no se ha podido encender");
                    return;
                }
            }
        }
        System.out.println("Dispositivo no encontrado.");
    }

    //Apaga un dispositivo (en el caso de que este encendido)
    public void apagarDispositivo() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el nombre del dispositivo: ");
        String nombre = entrada.nextLine();
        for (Dispositivo dispositivo : dispositivos) {
            if (dispositivo.getNombre().equalsIgnoreCase(nombre)) {
                if (dispositivo.isFuncionando()) {
                    dispositivo.setFuncionando(false);
                    System.out.println("El dispositivo se ha apagado.");
                    return;
                }

            }
        }
        System.out.println("Dispositivo no encontrado.");
    }

    //Calcula el consumo de todos los dispositivos encendidos
    public int calcularConsumo() {
        int total = 0;
        for (Dispositivo d : dispositivos) {
            //Si el dispositivo se encuentra encendido agrega la potencia al total
            if (d.isFuncionando()) {
                total += d.consumoReal();
            } else {
                System.out.println("No hay ningun dispositivo encendido");
            }
        }

        return total;
    }

    //Muestra todos los dispositivos creados
    public void listarDispositivos() {
        for (Dispositivo d : dispositivos) {
            System.out.println(d.toString());
        }
    }

}
