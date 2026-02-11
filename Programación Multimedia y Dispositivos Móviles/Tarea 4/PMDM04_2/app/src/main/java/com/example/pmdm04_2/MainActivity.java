package com.example.pmdm04_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.Color; // Importación necesaria para usar colores

/**
 * Actividad Principal (MainActivity) para la Tarea UT04.2.
 * Implementa la lógica para el Spinner de selección de colores y cambia el color del texto del resultado.
 * Autor: Jaime Moro Lopez
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerColores;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Carga el diseño XML apropiado (vertical o apaisado)
        setContentView(R.layout.activity_main);

        // 1. Inicializar vistas
        spinnerColores = findViewById(R.id.spinner_colores);
        tvResultado = findViewById(R.id.tv_resultado);

        // 2. Configurar el adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.seleccion_color, // Referencia al array de strings
                android.R.layout.simple_spinner_item // Layout por defecto para los ítems
        );

        // 3. Especificar el layout a usar cuando aparece la lista de opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 4. Aplicar el adaptador al Spinner
        spinnerColores.setAdapter(adapter);

        // 5. Establecer el listener para capturar la selección
        spinnerColores.setOnItemSelectedListener(this);
    }

    /**
     * Método invocado cuando se selecciona un ítem del Spinner.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Obtener el color seleccionado (p. ej., "Rojo")
        String colorSeleccionado = parent.getItemAtPosition(position).toString();

        // Obtener el texto base traducido (ej: "El color seleccionado es ")
        String textoBase = getString(R.string.color_seleccionado);

        // Actualizar el TextView con el resultado
        tvResultado.setText(textoBase + colorSeleccionado);

        // --- LÓGICA PARA CAMBIAR EL COLOR DEL TEXTO ---
        int colorInt = Color.BLACK; // Color por defecto

        // Mapear el nombre del color (string) al valor entero de Android
        switch (colorSeleccionado) {
            case "Rojo":
                colorInt = Color.RED;
                break;
            case "Azul":
                colorInt = Color.BLUE;
                break;
            case "Verde":
                colorInt = Color.GREEN;
                break;
            case "Naranja":
                // Android no tiene Color.ORANGE, usamos un valor RGB conocido
                colorInt = Color.rgb(255, 165, 0);
                break;
            default:
                // Si no se encuentra el color, se mantiene el negro por defecto
                colorInt = Color.BLACK;
                break;
        }

        // Aplicar el color determinado al texto del TextView
        tvResultado.setTextColor(colorInt);
        // ------------------------------------------------
    }

    /**
     * Método invocado cuando no se selecciona ningún ítem (por ejemplo, al principio o si el adaptador está vacío).
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No se requiere ninguna acción
    }
}