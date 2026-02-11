/**
 * Enumerado que define los tipos de peticiones y sus tiempos de impresión.
 */
public enum TipoPeticion {
    consulta(1000), informe(2000), proceso(3000);
    
    // Tiempo de simulación en milisegundos
    final int tiempo;
    
    TipoPeticion(int tiempo) { 
        this.tiempo = tiempo; 
    }
}