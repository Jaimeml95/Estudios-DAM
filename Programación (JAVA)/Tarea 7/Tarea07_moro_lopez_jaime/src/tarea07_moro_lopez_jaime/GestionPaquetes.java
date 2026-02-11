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
public class GestionPaquetes {

    public static Vehiculo buscarVehiculo(String matricula) {
        if (EmpresaReparto.listaVehiculos.get(matricula) != null) {
            System.out.println("Vehículo encontrado: " + EmpresaReparto.listaVehiculos.get(matricula));
            return EmpresaReparto.listaVehiculos.get(matricula);
        } else {
            System.out.println("Vehículo con matrícula " + matricula + " no encontrado.");
            return null;
        }
    }

    public static Paquete buscarPaquete(String codigo) {
        if (EmpresaReparto.listaPaquetes.get(codigo) != null) {
            System.out.println("Paquete encontrado: " + EmpresaReparto.listaPaquetes.get(codigo));
            return EmpresaReparto.listaPaquetes.get(codigo);
        } else {
            System.out.println("Paquete con codigo " + codigo + " no encontrado.");
            return null;
        }
    }

    public void aniadirVehiculo(EmpresaReparto empresa, Scanner entrada) {
        System.out.println("\n--- Añadir vehículo ---");
        System.out.println("Tipos de vehículo:");
        System.out.println("1. Coche");
        System.out.println("2. Moto");
        System.out.println("3. Bicicleta");
        System.out.println("4. Patinete");
        System.out.print("Selecciona tipo (1-4): ");

        byte tipo = Byte.parseByte(entrada.nextLine());

        System.out.print("Introduce matrícula: ");
        String matricula = entrada.nextLine();

        if (buscarVehiculo(matricula) != null) {
            System.out.println("Ya existe un vehículo con esa matrícula.");
            return;
        }

        System.out.print("Introduce marca: ");
        String marca = entrada.nextLine();
        System.out.print("Introduce modelo: ");
        String modelo = entrada.nextLine();

        Vehiculo nuevoVehiculo = null;

        switch (tipo) {
            case 1: // Coche
                System.out.print("Introduce capacidad de carga (litros): ");
                double capacidadCoche = Double.parseDouble(entrada.nextLine());
                System.out.print("Introduce autonomía del depósito (km): ");
                double autonomiaCoche = Double.parseDouble(entrada.nextLine());
                nuevoVehiculo = new Coche(matricula, marca, modelo, capacidadCoche, autonomiaCoche);
                break;
            case 2: // Moto
                System.out.print("Introduce autonomía del depósito (km): ");
                double autonomiaMoto = Double.parseDouble(entrada.nextLine());
                nuevoVehiculo = new Moto(matricula, marca, modelo, autonomiaMoto);
                break;
            case 3: // Bicicleta
                System.out.print("Introduce autonomía de la batería (km): ");
                double autonomiaBici = Double.parseDouble(entrada.nextLine());
                nuevoVehiculo = new Bicicleta(matricula, marca, modelo, autonomiaBici);
                break;
            case 4: // Patinete
                System.out.print("Introduce autonomía de la batería (km): ");
                double autonomiaPatinete = Double.parseDouble(entrada.nextLine());
                nuevoVehiculo = new Patinete(matricula, marca, modelo, autonomiaPatinete);
                break;
            default:
                System.out.println("Tipo de vehículo no válido.");
                return;
        }

        empresa.agregarVehiculo(nuevoVehiculo);
        System.out.println("Vehículo añadido correctamente.");
    }

    public void aniadirPaquete(EmpresaReparto empresa, Scanner entrada) {
        System.out.println("\n--- Añadir paquete ---");

        System.out.print("Introduce el código del paquete: ");
        String codigo = entrada.nextLine();

        if (buscarPaquete(codigo) != null) {
            System.out.println("❌ Ya existe un paquete con ese código.");
            return;
        }

        System.out.print("Introduce la dirección de destino: ");
        String direccionDestino = entrada.nextLine();

        System.out.print("Introduce el código postal de destino: ");
        short cpDestino = Short.parseShort(entrada.nextLine());

        System.out.print("El paquete está en reparto? (true/false): ");
        boolean enReparto = Boolean.parseBoolean(entrada.nextLine());

        System.out.print("Introduce el volumen del paquete (en litros): ");
        double volumen = Double.parseDouble(entrada.nextLine());

        Paquete nuevoPaquete = new Paquete(codigo, direccionDestino, cpDestino, enReparto, volumen);
        empresa.agregarPaquete(nuevoPaquete);

        System.out.println("✅ Paquete añadido correctamente.");
    }

    public void listarVehiculos() {
        if (EmpresaReparto.listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            System.out.println("Listado de vehículos:");
            for (Vehiculo v : EmpresaReparto.listaVehiculos.values()) {
                System.out.println(v);
            }
        }
    }

    public void listarPaquetes() {
        if (EmpresaReparto.listaPaquetes.isEmpty()) {
            System.out.println("No hay paquetes registrados.");
        } else {
            System.out.println("Listado de paquetes:");
            for (Paquete p : EmpresaReparto.listaPaquetes.values()) {
                System.out.println(p);
            }
        }
    }

    public void asignarPaqueteAVehiculo() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce la matrícula del vehículo: ");
        String matricula = entrada.nextLine();
        System.out.print("Introduce el código del paquete: ");
        String codigo = entrada.nextLine();

        Vehiculo vehiculo = buscarVehiculo(matricula);
        Paquete paquete = buscarPaquete(codigo);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
        } else if (paquete == null) {
            System.out.println("Paquete no encontrado.");
        } else if (paquete.isEnReparto()) {
            System.out.println("El paquete ya está en reparto.");
        } else if (vehiculo.obtenerCapacidadCarga() < paquete.getVolumen()) {
            System.out.println("El vehículo no tiene suficiente capacidad.");
        } else {
            vehiculo.agregarPaquete(paquete);
            paquete.setEnReparto(true);
            System.out.println("Paquete asignado correctamente al vehículo.");
        }
    }

    public void listaDeRepartos() {
        for (Vehiculo vehiculo : EmpresaReparto.listaVehiculos.values()) {
            System.out.println("Vehículo: " + vehiculo.getMatricula() + " (" + vehiculo.getMarca() + " " + vehiculo.getModelo() + ")");
            if (vehiculo.getListaReparto().isEmpty()) {
                System.out.println("  No tiene paquetes asignados.");
            } else {
                for (Paquete paquete : vehiculo.getListaReparto()) {
                    System.out.println("  - " + paquete);
                }
            }
        }
    }

    public void devolverPaquete() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce la matrícula del vehículo: ");
        String matricula = entrada.nextLine();
        System.out.print("Introduce el código del paquete: ");
        String codigo = entrada.nextLine();

        Vehiculo vehiculo = buscarVehiculo(matricula);
        Paquete paquete = buscarPaquete(codigo);

        if (vehiculo == null || paquete == null) {
            System.out.println("Vehículo o paquete no encontrado.");
            return;
        }

        if (vehiculo.getListaReparto().remove(paquete)) {
            paquete.setEnReparto(false);
            System.out.println("Paquete devuelto correctamente.");
        } else {
            System.out.println("El paquete no está asignado a ese vehículo.");
        }
    }

}
