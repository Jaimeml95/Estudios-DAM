import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase principal que dirige la simulación.
 */
public class FabLabMain {
    public static void main(String[] args) {
        GestorFabLab gestor = new GestorFabLab();
        int numClientes = 10;
        List<Thread> hilosClientes = new ArrayList<>();

        System.out.println("--- INICIO SIMULACIÓN FABLAB 3D CON BLOCKINGQUEUE ---");

        // Creación y arranque de los hilos de clientes
        for (int i = 1; i <= numClientes; i++) {
            String id = String.format("CLI-%03d", i);
            Thread t = new Thread(new Cliente(id, gestor));
            hilosClientes.add(t);
            t.start();
            
            // Pequeña pausa aleatoria entre la llegada de clientes
            try { Thread.sleep(new Random().nextInt(400)); } catch (InterruptedException e) {}
        }

        // Esperar a que todos los clientes terminen antes de mostrar el resumen
        for (Thread t : hilosClientes) {
            try {
                t.join();
            } catch (InterruptedException e) {}
        }

        // Mostrar estadísticas finales
        gestor.mostrarResumen();
        System.out.println("--- FIN DE LA SIMULACIÓN ---");
    }
}