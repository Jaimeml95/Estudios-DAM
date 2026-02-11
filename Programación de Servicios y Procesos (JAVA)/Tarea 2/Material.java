/**
 * Enumerado que define los tipos de materiales y sus tiempos de impresión.
 */
public enum Material {
    PLA(1800), PETG(2200), ABS(2600);
    
    // Tiempo de simulación en milisegundos
    final int tiempo;
    
    Material(int tiempo) { 
        this.tiempo = tiempo; 
    }
}