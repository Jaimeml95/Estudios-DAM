package com.example.pmdm04_3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

/**
 * Actividad Principal (MainActivity) para la Tarea UT04.3.
 * Implementa la lógica para el ListView de selección de ciudades y muestra el número de habitantes.
 * Autor: Jaime Moro Lopez
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvCiudades;
    private TextView tvResultadoHabitantes;

    // Almacenamiento de datos: HashMap para relacionar Ciudad con número de habitantes
    private HashMap<String, Integer> habitantesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Carga el diseño XML apropiado (activity_main o layout-land/activity_main)
        setContentView(R.layout.activity_main);

        // 1. Inicializar vistas
        lvCiudades = findViewById(R.id.lv_ciudades);
        tvResultadoHabitantes = findViewById(R.id.tv_resultado_habitantes);

        // 2. Inicializar los datos de habitantes (Valores ficticios para demostración)
        inicializarHabitantesData();

        // 3. Establecer el listener para capturar la selección en el ListView
        // Nota: No se necesita un Adapter, ya que el ListView usa 'android:entries' del XML,
        // pero sí necesitamos el listener.
        lvCiudades.setOnItemClickListener(this);
    }

    /**
     * Inicializa el HashMap con las ciudades y sus respectivos números de habitantes.
     */
    private void inicializarHabitantesData() {
        habitantesData = new HashMap<>();
        // Datos de población aproximados (cifras ficticias)
        habitantesData.put("Madrid", 3280000);
        habitantesData.put("Barcelona", 1620000);
        habitantesData.put("Valencia", 800000);
        habitantesData.put("Sevilla", 690000);
    }

    /**
     * Método invocado cuando se hace clic en un ítem del ListView.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Obtener el nombre de la ciudad seleccionada
        String ciudadSeleccionada = (String) parent.getItemAtPosition(position);

        // Buscar el número de habitantes en el HashMap
        Integer habitantes = habitantesData.get(ciudadSeleccionada);

        if (habitantes != null) {
            // Formatear el número de habitantes con separadores de miles para mejor lectura
            String habitantesFormateados = String.format(Locale.getDefault(), "%,d", habitantes);

            // Obtener el formato de la cadena de resultado (Nº de habitantes de %1$s: %2$s)
            String textoResultado = getString(R.string.resultado_habitantes, ciudadSeleccionada, habitantesFormateados);

            // Actualizar el TextView con el resultado
            tvResultadoHabitantes.setText(textoResultado);

        } else {
            // En caso de que la ciudad no se encuentre en el mapa (situación improbable aquí)
            tvResultadoHabitantes.setText(getString(R.string.resultado_inicial));
            Toast.makeText(this, "Error al obtener datos de la ciudad.", Toast.LENGTH_SHORT).show();
        }
    }
}