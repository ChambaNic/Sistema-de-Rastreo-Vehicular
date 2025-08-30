package com.example.prueba;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.prueba.adapter.RutaAdapter;
import com.example.prueba.model.Ruta;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RutasActivity extends AppCompatActivity {

    RecyclerView rvRutas;
    Button btnAgregarRuta, btnIniciarRastreo;
    ArrayList<Ruta> listaRutas;
    RutaAdapter adapter;

    private final String ARCHIVO = "rutas.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);

        rvRutas = findViewById(R.id.rvRutas);
        btnAgregarRuta = findViewById(R.id.btnAgregarRuta);
        btnIniciarRastreo = findViewById(R.id.btnIniciarRastreo);

        listaRutas = new ArrayList<>();
        adapter = new RutaAdapter(listaRutas);
        rvRutas.setLayoutManager(new LinearLayoutManager(this));
        rvRutas.setAdapter(adapter);

        // ✅ cargar rutas en un hilo secundario
        new Thread(() -> {
            cargarRutas();
            runOnUiThread(() -> adapter.notifyDataSetChanged());
        }).start();

        btnAgregarRuta.setOnClickListener(v -> {
            Ruta rNueva = new Ruta("30/08/2025", "Juan Perez", "ABC-123", 5);
            listaRutas.add(rNueva);
            adapter.notifyDataSetChanged();
            guardarRuta(rNueva);
            Toast.makeText(this, "Ruta agregada", Toast.LENGTH_SHORT).show();
        });

        // ✅ Simulación de rastreo en tiempo real (5 actualizaciones cada 5 seg)
        btnIniciarRastreo.setOnClickListener(v -> {
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                    int numero = i;
                    runOnUiThread(() ->
                            Toast.makeText(this, "Actualización de rastreo #" + numero, Toast.LENGTH_SHORT).show()
                    );
                }
            }).start();
        });
    }

    // 🔹 Método para leer rutas desde rutas.txt
    private void cargarRutas() {
        try {
            FileInputStream fis = openFileInput(ARCHIVO);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    listaRutas.add(new Ruta(
                            partes[0],
                            partes[1],
                            partes[2],
                            Integer.parseInt(partes[3])
                    ));
                }
            }
            reader.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Método para guardar rutas en rutas.txt
    private void guardarRuta(Ruta r) {
        try {
            FileOutputStream fos = openFileOutput(ARCHIVO, MODE_APPEND);
            String linea = r.fecha + "," + r.conductor + "," + r.vehiculo + "," + r.numeroPaquetes + "\n";
            fos.write(linea.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}