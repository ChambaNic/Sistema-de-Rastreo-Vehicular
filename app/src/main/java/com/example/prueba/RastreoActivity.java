package com.example.prueba;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RastreoActivity extends AppCompatActivity {

    Button btnIniciarRastreo;
    TextView tvEstadoRastreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastreo);

        btnIniciarRastreo = findViewById(R.id.btnIniciarRastreo);
        tvEstadoRastreo = findViewById(R.id.tvEstadoRastreo);

        btnIniciarRastreo.setOnClickListener(v -> {
            new Thread(() -> {
                for(int i=1; i<=5; i++){
                    try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }

                    int numero = i;
                    runOnUiThread(() -> {
                        String msg = "Ubicación enviada #" + numero;
                        tvEstadoRastreo.setText(msg);
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    });
                }
            }).start();
        });
    }
}
