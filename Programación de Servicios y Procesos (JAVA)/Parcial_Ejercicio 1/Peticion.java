import java.util.Random;

public class Peticion implements Runnable{
    private final String id;
    private final TipoPeticion tipoPeticion;
    private final GestorServidor gestor;

   public Peticion(String id, GestorServidor gestor) {
        this.id = id;
        this.gestor = gestor;
        // Asignación de tipos de peticiones aleatorias para el trabajo
        this.tipoPeticion = TipoPeticion.values()[new Random().nextInt(TipoPeticion.values().length)];
    } 
    @Override
    public void run() {
        String servidorAsignado = null;
        try {
            System.out.println("[COLA] " + id + " ha llegado y espera con peticion tipo " + tipoPeticion);
            
            // Intenta obtener un servidor (se bloquea si están todos ocupados)
            servidorAsignado = gestor.ocuparServidor();
            
            System.out.println("[ASIGNACIÓN] " + id + " -> " + servidorAsignado);
            System.out.println("[" + servidorAsignado + "] Iniciando impresión de " + id + " (" + tipoPeticion + ")...");
            
            // Simulación del proceso de ejecución
            Thread.sleep(tipoPeticion.tiempo);
            
            System.out.println("[" + servidorAsignado + "] " + id + " FINALIZADO correctamente.");

        } catch (InterruptedException e) {
            System.err.println("Error en el hilo " + id + ": " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            // Se asegura de liberar el servidor pase lo que pase
            if (servidorAsignado != null) {
                gestor.liberarServidor(servidorAsignado, tipoPeticion);
            }
        }
    }
}
