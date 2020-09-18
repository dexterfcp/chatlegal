package com.legal.fcp.conderechoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VentanaInicialLogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_inicial_logo);

        Button btnDiccionario = findViewById(R.id.btnDiccionario);

        btnDiccionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrinDiccionario.class);
                startActivity(intent);
            }
        });

        Button btnPreguntasFrec = findViewById(R.id.btnPreguntasFrec);

        btnPreguntasFrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrinDiccionario.class);
                intent.putExtra("isPreguntas", true);
                startActivity(intent);
            }
        });


    }
}
