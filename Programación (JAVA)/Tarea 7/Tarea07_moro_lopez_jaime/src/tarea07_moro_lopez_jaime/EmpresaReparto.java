/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea07_moro_lopez_jaime;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author tpmvo
 */
public class EmpresaReparto {

    static Map<String, Vehiculo> listaVehiculos;  // Almacena los vehículos por matrícula
    static Map<String, Paquete> listaPaquetes;    // Almacena los paquetes por código

    public EmpresaReparto() {
        this.listaVehiculos = new HashMap<>();
        this.listaPaquetes = new LinkedHashMap<>();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        listaVehiculos.put(vehiculo.getMatricula(), vehiculo);
    }

    public void agregarPaquete(Paquete paquete) {
        listaPaquetes.put(paquete.getCodigo(), paquete);
    }

    public void agregarPaqueteAVehiculo(String matricula, String codigo) {
        Vehiculo vehiculo = listaVehiculos.get(matricula);
        Paquete paquete = listaPaquetes.get(codigo);
        if (vehiculo == null) {
            System.out.println("Vehículo con matrícula " + vehiculo.getMatricula() + " no encontrado.");
            return;
        }

        if (paquete == null) {
            System.out.println("Paquete con código " + codigo + " no encontrado.");
            return;
        }

        if (paquete.isEnReparto()) {
            System.out.println("El paquete " + codigo + " ya está en reparto.");
            return;
        }

        if (vehiculo.obtenerCapacidadCarga() < paquete.getVolumen()) {
            System.out.println("No hay suficiente espacio para cargar el paquete en el vehículo");
            return;
        }

        paquete.setEnReparto(true);

        if (!vehiculo.agregarPaquete(paquete)) {
            System.out.println("No se ha podido cargar el paquete");
        } else {
            System.out.println("Paquete cargado correctamente");
        }
    }
}
