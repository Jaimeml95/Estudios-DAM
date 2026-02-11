import java.io.*;
import java.util.*;

public class CoordinarProcesos {

    private static final double DEFAULT_MIN_ALERT = 30.0;
    private static final String CLASS_TO_RUN = "TransformarLecturas";
    
    public static void main(String[] args) {
        
        // 1. Parseo de argumentos: <entrada.csv> <N> <salida_final.csv> [--minAlert=umbral]
        if (args.length < 3) {
            System.err.println("Uso: java CoordinarProcesos <entrada.csv> <N> <salida_final.csv> [--minAlert=umbral]");
            return;
        }

        String inputFile = args[0];
        int N = 0;
        try {
            N = Integer.parseInt(args[1]);
            if (N <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("El número de procesos (N) debe ser un entero positivo.");
            return;
        }
        String finalOutputFile = args[2];
        double minAlert = DEFAULT_MIN_ALERT;
        
        // Procesar argumento opcional --minAlert
        for (int i = 3; i < args.length; i++) {
            if (args[i].startsWith("--minAlert=")) {
                try {
                    String val = args[i].substring("--minAlert=".length());
                    minAlert = Double.parseDouble(val);
                } catch (NumberFormatException e) {
                    System.err.println("Umbral de alerta inválido. Usando por defecto: " + DEFAULT_MIN_ALERT);
                }
            }
        }
        
        // 2. División del archivo de entrada
        List<String> tempInputFiles = new ArrayList<>();
        List<String> partialOutputFiles = new ArrayList<>();
        
        // Intentar dividir el archivo
        if (!splitFile(inputFile, N, tempInputFiles, partialOutputFiles)) {
            System.err.println("Fallo al preparar los archivos temporales.");
            return;
        }
        
        // 3. Lanzamiento de instancias de TransformarLecturas (Worker Processes)
        List<Process> processes = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            String tempInput = tempInputFiles.get(i);
            String partialOutput = partialOutputFiles.get(i);

            // Construcción del comando para ProcessBuilder
            List<String> command = new ArrayList<>();
            command.add("java");
            command.add(CLASS_TO_RUN); // java TransformarLecturas
            command.add(tempInput);    // <temp_input_i.csv>
            command.add(partialOutput); // <salida_parcial_i.csv>
            
            // Añadir el argumento opcional si se usó
            if (minAlert != DEFAULT_MIN_ALERT) {
                command.add("--minAlert=" + minAlert);
            }

            try {
                // Lanzar el proceso con ProcessBuilder.start()
                ProcessBuilder pb = new ProcessBuilder(command);
                Process p = pb.start(); 
                processes.add(p);
            } catch (IOException e) {
                System.err.println("Error al lanzar proceso " + i + ": " + e.getMessage());
                cleanup(tempInputFiles, partialOutputFiles);
                return; 
            }
        }
        
        // 4. Coordinación (Esperar a que todos terminen con process.waitFor())
        waitForProcesses(processes);
        System.out.println("Todos los procesos parciales han terminado.");


        // 5. Fusión de los archivos de salida parciales
        combineFiles(partialOutputFiles, finalOutputFile);

        // 6. Limpieza de archivos temporales
        cleanup(tempInputFiles, partialOutputFiles);
    }
    
    // ========================================================================
    // MÉTODOS AUXILIARES
    // ========================================================================

    // Función que divide el fichero de entrada en N ficheros temporales.
    private static boolean splitFile(String inputFile, int N, List<String> tempInputFiles, List<String> partialOutputFiles) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            
            // Contar el número total de líneas (para la división equitativa)
            long totalLines = 0;
            // Primero, leer y guardar la cabecera
            String header = br.readLine(); 
            if (header == null) return true; // Archivo vacío
            
            // Contar líneas de datos
            while (br.readLine() != null) totalLines++;
            
            // Calcular líneas de datos por proceso
            long dataLines = totalLines;
            int linesPerProcess = (int) Math.ceil((double) dataLines / N);
            if (linesPerProcess == 0 && dataLines > 0) linesPerProcess = 1;

            System.out.printf("Total datos: %d. Procesos: %d. Líneas/proceso: %d%n", dataLines, N, linesPerProcess);

            // Reiniciar el lector para la división
            br.close();
            BufferedReader brSplit = new BufferedReader(new FileReader(inputFile));
            brSplit.readLine(); // Saltar la cabecera ya leída
            
            // Bucle de división
            for (int i = 0; i < N; i++) {
                File tempInput = new File(String.format("temp_input_%d.csv", i));
                tempInputFiles.add(tempInput.getName());
                partialOutputFiles.add(String.format("salida_parcial_%d.csv", i));

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempInput))) {
                    
                    bw.write(header); // Cada fichero TEMPORAL necesita la cabecera
                    bw.newLine();
                    
                    int linesWritten = 0;
                    String dataLine;
                    
                    // Escribir el trozo de datos
                    while (linesWritten < linesPerProcess && (dataLine = brSplit.readLine()) != null) {
                        bw.write(dataLine);
                        bw.newLine();
                        linesWritten++;
                    }
                }
            }
            brSplit.close();
            return true;

        } catch (IOException e) {
            System.err.println("Error durante la división del archivo: " + e.getMessage());
            return false;
        }
    }

    // Función que espera la finalización de todos los procesos
    private static void waitForProcesses(List<Process> processes) {
        for (int i = 0; i < processes.size(); i++) {
            try {
                int exitCode = processes.get(i).waitFor(); 
                if (exitCode != 0) {
                    System.err.println("Advertencia: Proceso parcial " + i + " terminó con código de error: " + exitCode);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El proceso " + i + " fue interrumpido.");
            }
        }
    }

    // Función que combina los ficheros de salida parciales
    private static void combineFiles(List<String> partialOutputFiles, String finalOutputFile) {
        try (BufferedWriter finalWriter = new BufferedWriter(new FileWriter(finalOutputFile))) {
            
            boolean firstFile = true;
            
            for (String partialFile : partialOutputFiles) {
                File file = new File(partialFile);
                if (!file.exists()) continue; 

                try (BufferedReader partialReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    boolean isHeader = true;

                    while ((line = partialReader.readLine()) != null) {
                        if (isHeader) {
                            isHeader = false;
                            if (firstFile) {
                                finalWriter.write(line); // Escribir la cabecera SOLO del primer fichero
                                finalWriter.newLine();
                                firstFile = false;
                            }
                            continue; // Saltar cabecera en el resto de ficheros
                        }
                        
                        finalWriter.write(line);
                        finalWriter.newLine();
                    }
                }
            }
            System.out.println("Fusión completada en " + finalOutputFile);

        } catch (IOException e) {
            System.err.println("Error durante la fusión de ficheros: " + e.getMessage());
        }
    }

    // Función para eliminar archivos temporales
    private static void cleanup(List<String> inputFiles, List<String> outputFiles) {
        for (String file : inputFiles) {
             new File(file).delete();
        }
        for (String file : outputFiles) {
             new File(file).delete();
        }
    }
}