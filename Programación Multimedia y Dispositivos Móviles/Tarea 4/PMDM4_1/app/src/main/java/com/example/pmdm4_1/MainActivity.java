package com.example.pmdm4_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Actividad Principal (MainActivity) para la Tarea UT04.1.
 * Autor: Jaime Moro López
 *
 * Esta actividad carga la interfaz de usuario definida en
 * res/layout/activity_main.xml (Portrait) o res/layout-land/activity_main.xml (Landscape),
 * mostrando los botones de las estaciones del año.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carga el diseño XML apropiado (vertical o apaisado)
        // según la orientación del dispositivo.
        setContentView(R.layout.activity_main);

        // Dado que la tarea no requiere funcionalidad de botones,
        // no es necesario inicializar ni asignar listeners aquí.
        // Solo con cargar el layout es suficiente para demostrar
        // la orientación y localización.
    }
}