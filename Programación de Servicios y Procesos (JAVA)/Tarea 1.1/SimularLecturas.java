import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Locale;

public class SimularLecturas {
    
    private static final Random RANDOM = new Random();
    // Incluimos intencionadamente "S_OUT" para simular datos que LimpiarYOrdenarLecturas debe descartar o gestionar.
    private static final String[] SENSORS = {"S1", "S2", "S3", "S4", "S5", "S_OUT"}; 
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        
        final int NUM_LECTURAS = 50; 
        
        // Rango ampliado para incluir valores fuera de [-20, 60] (para prueba de filtro)
        final double MIN_VALOR = -30.0;
        final double MAX_VALOR = 70.0;

        for (int i = 0; i < NUM_LECTURAS; i++) {
            
            // 1. ID de sensor aleatorio
            String sensorId = SENSORS[RANDOM.nextInt(SENSORS.length)];
            
            // 2. Valor aleatorio con dos decimales
            double valor = ThreadLocalRandom.current().nextDouble(MIN_VALOR, MAX_VALOR);
            
            // 3. Timestamp reciente y formateado
            LocalDateTime timestamp = LocalDateTime.now()
                .minusMinutes(RANDOM.nextInt(120))
                .minusSeconds(RANDOM.nextInt(60));
            String formattedTimestamp = timestamp.format(FORMATTER);
            
            // 4. Imprimir la línea CSV a STDOUT (que será la entrada del siguiente proceso)
            // Usamos Locale.US para asegurar el punto decimal en el valor.
            System.out.printf(Locale.US, "%s;%s;%.2f%n", sensorId, formattedTimestamp, valor);
        }
    }
}