import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase principal que dirige la simulación.
 */
public class SistemaMain {
    public static void main(String[] args) {
        GestorServidor gestor = new GestorServidor();
        int numPeticiones = 10;
        List<Thread> hilosPeticiones = new ArrayList<>();

        System.out.println("--- INICIO SIMULACIÓN SISTEMA CON BLOCKINGQUEUE ---");

        // Creación y arranque de los hilos de peticiones
        for (int i = 1; i <= numPeticiones; i++) {
            String id = String.format("SERVER-%03d", i);
            Thread t = new Thread(new Peticion(id, gestor));
            hilosPeticiones.add(t);
            t.start();
            
            // Pequeña pausa aleatoria entre la llegada de peticiones
            try { Thread.sleep(new Random().nextInt(400)); } catch (InterruptedException e) {}
        }

        // Esperar a que todas las peticiones terminen antes de mostrar el resumen
        for (Thread t : hilosPeticiones) {
            try {
                t.join();
            } catch (InterruptedException e) {}
        }

        // Mostrar estadísticas finales
        gestor.mostrarResumen();
        System.out.println("--- FIN DE LA SIMULACIÓN ---");
    }
}