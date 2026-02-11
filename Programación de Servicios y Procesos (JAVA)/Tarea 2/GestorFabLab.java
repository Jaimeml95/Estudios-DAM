import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Clase que gestiona los recursos compartidos (impresoras) de forma segura.
 */
public class GestorFabLab {
    // Cola que almacena las impresoras disponibles y bloquea si no hay ninguna
    private final BlockingQueue<String> colaImpresoras;
    // Mapa seguro para hilos que almacena las estadísticas de uso
    private final Map<String, Map<Material, Integer>> estadisticas;

    public GestorFabLab() {
        this.colaImpresoras = new LinkedBlockingQueue<>(3);
        this.estadisticas = new ConcurrentHashMap<>();
        
        // Inicialización de las 3 impresoras disponibles
        String[] nombres = {"IMP-1", "IMP-2", "IMP-3"};
        for (String nombre : nombres) {
            colaImpresoras.add(nombre);
            estadisticas.put(nombre, new ConcurrentHashMap<>());
        }
    }

    // Método bloqueante para obtener una impresora libre
    public String ocuparImpresora() throws InterruptedException {
        return colaImpresoras.take(); // Espera automáticamente si la cola está vacía
    }

    // Método para devolver la impresora y registrar el trabajo
    public void liberarImpresora(String nombre, Material mat) {
        // Actualizar el conteo de materiales por impresora
        Map<Material, Integer> conteo = estadisticas.get(nombre);
        conteo.put(mat, conteo.getOrDefault(mat, 0) + 1);

        System.out.println("[SISTEMA] " + nombre + " queda LIBRE.");
        colaImpresoras.offer(nombre); // Introduce la impresora de nuevo en la cola
    }

    // Imprime el informe final de actividad
    public void mostrarResumen() {
        System.out.println("\n========================================");
        System.out.println("   RESUMEN FINAL DE TRABAJOS FABLAB");
        System.out.println("========================================");
        estadisticas.forEach((impresora, trabajos) -> {
            System.out.println("[" + impresora + "]: " + trabajos);
        });
        System.out.println("========================================\n");
    }
}