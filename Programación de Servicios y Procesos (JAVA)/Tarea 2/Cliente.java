import java.util.Random;

/**
 * Representa a un cliente que solicita un trabajo de impresión (Hilo).
 */
public class Cliente implements Runnable {
    private final String id;
    private final Material material;
    private final GestorFabLab gestor;

    public Cliente(String id, GestorFabLab gestor) {
        this.id = id;
        this.gestor = gestor;
        // Asignación de material aleatorio para el trabajo
        this.material = Material.values()[new Random().nextInt(Material.values().length)];
    }

    @Override
    public void run() {
        String impresoraAsignada = null;
        try {
            System.out.println("[COLA] " + id + " ha llegado y espera con material " + material);
            
            // Intenta obtener una impresora (se bloquea si están todas ocupadas)
            impresoraAsignada = gestor. ocuparImpresora();
            
            System.out.println("[ASIGNACIÓN] " + id + " -> " + impresoraAsignada);
            System.out.println("[" + impresoraAsignada + "] Iniciando impresión de " + id + " (" + material + ")...");
            
            // Simulación del proceso de impresión
            Thread.sleep(material.tiempo);
            
            System.out.println("[" + impresoraAsignada + "] " + id + " FINALIZADO correctamente.");

        } catch (InterruptedException e) {
            System.err.println("Error en el hilo " + id + ": " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            // Se asegura de liberar la impresora pase lo que pase
            if (impresoraAsignada != null) {
                gestor.liberarImpresora(impresoraAsignada, material);
            }
        }
    }
}