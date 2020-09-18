package com.legal.fcp.conderechoapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.legal.fcp.conderechoapp.adapters.AdaptadorPalabras;
import com.legal.fcp.conderechoapp.basedatos.conexion.ContenedorConexion;
import com.legal.fcp.conderechoapp.basedatos.dao.PalabraDAO;
import com.legal.fcp.conderechoapp.basedatos.dao.PreguntaDAO;
import com.legal.fcp.conderechoapp.basedatos.vo.Palabra;
import com.legal.fcp.conderechoapp.basedatos.vo.Pregunta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrinDiccionario extends AppCompatActivity {

    Boolean isPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        isPreguntas = false;
        if(extras!= null && extras.getSerializable("isPreguntas") != null){
            isPreguntas = (Boolean) extras.getSerializable("isPreguntas");
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ChatPrincipal.class));
            }
        });

        final ListView listaPalabrasView = findViewById(R.id.lista_palabras);
        final ArrayList<Palabra> listadoPalabras = new ArrayList<>();
        final ArrayList<Pregunta> listadoPreguntas = new ArrayList<>();

        listaPalabrasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = parent.getItemAtPosition(position);
                Palabra  msj = null;
                if (obj != null && obj instanceof Palabra){
                    msj = (Palabra) obj;
                }
                Intent intent = new Intent(getApplicationContext(), SignificadoPal.class);
                intent.putExtra("palabra", msj);
                startActivity(intent);
            }
        });
        final AdaptadorPalabras adaptadorPalabras = new AdaptadorPalabras(this,listadoPalabras, listadoPreguntas, isPreguntas);

        listaPalabrasView.setAdapter(adaptadorPalabras);

        if (isPreguntas){
            PreguntaDAO daoPre = new PreguntaDAO(ContenedorConexion.getDbConderecho());
            daoPre.buscarPalabra("", adaptadorPalabras, listaPalabrasView);
        }else {
            PalabraDAO daoPal = new PalabraDAO(ContenedorConexion.getDbConderecho());
            daoPal.buscarPalabra("", adaptadorPalabras, listaPalabrasView);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if (isPreguntas){
            return false;
        }

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu3_buscar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PalabraDAO daoPal = new PalabraDAO(ContenedorConexion.getDbConderecho());
                final ListView listaPalabrasView = findViewById(R.id.lista_palabras);
                final AdaptadorPalabras adaptadorPalabras = new AdaptadorPalabras(PrinDiccionario.this,new ArrayList<Palabra>(), null, false);
                daoPal.buscarPalabra(query,adaptadorPalabras,listaPalabrasView);
                Toast.makeText(PrinDiccionario.this, "Buscando .... " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    PalabraDAO daoPal = new PalabraDAO(ContenedorConexion.getDbConderecho());
                    final ListView listaPalabrasView = findViewById(R.id.lista_palabras);
                    final AdaptadorPalabras adaptadorPalabras = new AdaptadorPalabras(PrinDiccionario.this,new ArrayList<Palabra>(), null, false);
                    daoPal.buscarPalabra(newText,adaptadorPalabras,listaPalabrasView);
                    Toast.makeText(PrinDiccionario.this, "Listando .... ", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
