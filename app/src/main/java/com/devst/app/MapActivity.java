package com.devst.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

//  EVENTO EXPLÍCITO 3: MainActivity → MapActivity (ver ubicación)
public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Button btnAbrirMapa = findViewById(R.id.btnAbrirMapa);

        btnAbrirMapa.setOnClickListener(v -> {
            // Abrir Google Maps en la ubicación institucional
            Uri ubicacion = Uri.parse("geo:-33.4489,-70.6693?q=Santo+Tomás+Santiago");
            Intent intent = new Intent(Intent.ACTION_VIEW, ubicacion);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });
    }
}
