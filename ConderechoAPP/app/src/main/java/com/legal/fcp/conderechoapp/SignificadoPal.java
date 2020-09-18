package com.legal.fcp.conderechoapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.legal.fcp.conderechoapp.basedatos.vo.Palabra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class SignificadoPal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_significado_pal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Bundle extras = getIntent().getExtras();
        Palabra palabra = (Palabra)extras.getSerializable("palabra");
        TextView textoSignificadoView  = findViewById(R.id.textoSignificado);
        this.setTitle(palabra.getPalabraVisual());
        textoSignificadoView.setText(palabra.getSignificado());



    }
}
