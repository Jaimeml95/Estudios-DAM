import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Clase que gestiona los recursos compartidos (servidores) de forma segura.
 */
public class GestorServidor {
    // Cola que almacena los servidores disponibles y bloquea si no hay ninguna
    private final BlockingQueue<String> colaServidores;
    // Mapa seguro para hilos que almacena las estadísticas de uso
    private final Map<String, Map<TipoPeticion, Integer>> estadisticas;

    public GestorServidor() {
        this.colaServidores = new LinkedBlockingQueue<>(3);
        this.estadisticas = new ConcurrentHashMap<>();
        
        // Inicialización de los 3 servidores disponibles
        String[] nombres = {"SERVER-1", "SERVER-2", "SERVER-3"};
        for (String nombre : nombres) {
            colaServidores.add(nombre);
            estadisticas.put(nombre, new ConcurrentHashMap<>());
        }
    }

    // Método bloqueante para obtener un servidorlibre
    public String ocuparServidor() throws InterruptedException {
        return colaServidores.take(); // Espera automáticamente si la cola está vacía
    }

    // Método para devolver el servidor y registrar el trabajo
    public void liberarServidor(String nombre, TipoPeticion tipo) {
        // Actualizar el conteo de peticiones por servidor
        Map<TipoPeticion, Integer> conteo = estadisticas.get(nombre);
        conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);

        System.out.println("[SISTEMA] " + nombre + " queda LIBRE.");
        colaServidores.offer(nombre); // Introduce el servidor de nuevo en la cola
    }

    // Imprime el informe final de actividad
    public void mostrarResumen() {
        System.out.println("\n=================================================");
        System.out.println("   RESUMEN FINAL DE TRABAJOS DE LOS SERVIDORES");
        System.out.println("=================================================");
        estadisticas.forEach((servidor, peticiones) -> {
            System.out.println("[" + servidor + "]: " + peticiones);
        });
        System.out.println("========================================\n");
    }
}
