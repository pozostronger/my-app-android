package com.devst.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // Recuperar datos enviados desde HomeActivity
        String nombre = getIntent().getStringExtra("nombre");
        String nota = getIntent().getStringExtra("nota");
        String asignatura = getIntent().getStringExtra("asignatura");

        // Establecer los detalles en el TextView
        TextView tvDetalle = findViewById(R.id.tvDetalle);
        tvDetalle.setText("Alumno: " + nombre + "\nNota: " + nota + "\nAsignatura: " + asignatura);

        // Configurar el botón "Volver"
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            // Regresar a la actividad anterior
            onBackPressed();  // Esto hace lo mismo que llamar a finish()
        });
    }
}
