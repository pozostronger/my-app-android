package com.devst.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class HomeActivity extends AppCompatActivity {

    private String emailUsuario = "";
    private TextView tvBienvenida;
    private Button btnLinterna;
    private CameraManager camara;
    private String camaraID = null;
    private boolean luz = false;

    // Lanzadores
    private final ActivityResultLauncher<Intent> editarPerfilLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String nombre = result.getData().getStringExtra("nombre_editado");
                    if (nombre != null) {
                        tvBienvenida.setText("Hola, " + nombre);
                    }
                }
            });

    private final ActivityResultLauncher<String> permisoCamaraLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (granted) {
                    alternarluz();
                } else {
                    Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Referencias
        tvBienvenida = findViewById(R.id.tvBienvenida);
        Button btnIrPerfil = findViewById(R.id.btnIrPerfil);
        Button btnAulas = findViewById(R.id.btnAulas);
        Button btnEnviarCorreo = findViewById(R.id.btnEnviarCorreo);
        Button btnCompartir = findViewById(R.id.btnCompartir);
        btnLinterna = findViewById(R.id.btnLinterna);
        Button btnCamara = findViewById(R.id.btnCamara);
        Button btnConfiguracion = findViewById(R.id.btnConfiguracion);
        Button btnEnviarSMS = findViewById(R.id.btnEnviarSMS);
        Button btnContactos = findViewById(R.id.btnContactos);
        Button btnLlamar = findViewById(R.id.btnLlamar);

        // NUEVOS BOTONES (Eventos explícitos)
        Button btnDetalle = findViewById(R.id.btnDetalle);
        Button btnConfigInterna = findViewById(R.id.btnConfigInterna);
        Button btnMapa = findViewById(R.id.btnMapa);

        // Datos recibidos
        emailUsuario = getIntent().getStringExtra("email_usuario");
        if (emailUsuario == null) emailUsuario = "";
        tvBienvenida.setText("Bienvenido: " + emailUsuario);

        // Ir a perfil
        btnIrPerfil.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, PerfilActivity.class);
            i.putExtra("email_usuario", emailUsuario);
            editarPerfilLauncher.launch(i);
        });

        // Evento implícito Nº1: Abrir página institucional (Aulas Virtuales)
        btnAulas.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://aulasvirtuales.santotomas.cl");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        // Enviar correo
        btnEnviarCorreo.setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setData(Uri.parse("mailto:"));
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailUsuario});
            email.putExtra(Intent.EXTRA_SUBJECT, "Prueba desde la app");
            email.putExtra(Intent.EXTRA_TEXT, "Hola, esto es un correo de prueba.");
            startActivity(Intent.createChooser(email, "Enviar correo con:"));
        });

        // Compartir texto
        btnCompartir.setOnClickListener(v -> {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "Hola desde mi app Android 😎");
            startActivity(Intent.createChooser(share, "Compartir usando:"));
        });

        // Linterna
        camara = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            for (String id : camara.getCameraIdList()) {
                CameraCharacteristics cc = camara.getCameraCharacteristics(id);
                Boolean disponibleFlash = cc.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                Integer lensFacing = cc.get(CameraCharacteristics.LENS_FACING);
                if (Boolean.TRUE.equals(disponibleFlash)
                        && lensFacing != null
                        && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                    camaraID = id;
                    break;
                }
            }
        } catch (CameraAccessException e) {
            Toast.makeText(this, "No se puede acceder a la cámara", Toast.LENGTH_SHORT).show();
        }

        btnLinterna.setOnClickListener(v -> {
            if (camaraID == null) {
                Toast.makeText(this, "Este dispositivo no tiene flash", Toast.LENGTH_SHORT).show();
                return;
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                alternarluz();
            } else {
                permisoCamaraLauncher.launch(Manifest.permission.CAMERA);
            }
        });

        btnCamara.setOnClickListener(v -> startActivity(new Intent(this, CamaraActivity.class)));

        // Evento implícito Nº2: Abrir configuración del sistema
        btnConfiguracion.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_SETTINGS)));

        // Evento implícito Nº3: Enviar SMS
        btnEnviarSMS.setOnClickListener(v -> startActivity(new Intent(this, EnviarSMSActivity.class)));

        // Evento implícito Nº4: Ver contactos del teléfono
        btnContactos.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI)));

        // Evento implícito Nº5: Llamar (mostrar marcador telefónico)
        btnLlamar.setOnClickListener(v -> {
            Intent intentLlamada = new Intent(Intent.ACTION_DIAL);
            intentLlamada.setData(Uri.parse("tel:912345678"));
            startActivity(intentLlamada);
        });

        // EVENTO EXPLÍCITO 1 → MainActivity -> DetalleActivity
        btnDetalle.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra("nombre", "Juan Pérez");
            intent.putExtra("nota", "6.8");
            intent.putExtra("asignatura", "Programación Android");
            startActivity(intent);
        });

        // EVENTO EXPLÍCITO 2 → MainActivity -> ConfigActivity
        btnConfigInterna.setOnClickListener(v ->
                startActivity(new Intent(this, ConfigActivity.class)));

        // EVENTO EXPLÍCITO 3 → MainActivity -> MapActivity
        btnMapa.setOnClickListener(v ->
                startActivity(new Intent(this, MapActivity.class)));
    }

    // Alternar linterna
    private void alternarluz() {
        try {
            luz = !luz;
            camara.setTorchMode(camaraID, luz);
            btnLinterna.setText(luz ? "Apagar Linterna" : "Encender Linterna");
        } catch (CameraAccessException e) {
            Toast.makeText(this, "Error al controlar la linterna", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (camaraID != null && luz) {
            try {
                camara.setTorchMode(camaraID, false);
                luz = false;
                btnLinterna.setText("Encender Linterna");
            } catch (CameraAccessException ignored) {}
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_perfil) {
            Intent i = new Intent(this, PerfilActivity.class);
            i.putExtra("email_usuario", emailUsuario);
            editarPerfilLauncher.launch(i);
            return true;
        } else if (id == R.id.action_web) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com")));
            return true;
        } else if (id == R.id.action_salir) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
