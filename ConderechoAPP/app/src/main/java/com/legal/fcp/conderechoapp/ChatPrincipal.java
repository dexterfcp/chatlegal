package com.legal.fcp.conderechoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.legal.fcp.conderechoapp.adapters.AdaptadorMensajes;
import com.legal.fcp.conderechoapp.objetos.Mensajes;

import java.util.ArrayList;
import java.util.List;

public class ChatPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_principal);
        final ListView listaMensajesView = findViewById(R.id.listaMensajes);
        final List<Mensajes> listaMensajes = new ArrayList<Mensajes>();
        final AdaptadorMensajes adaptadorMensajes = new AdaptadorMensajes(this,listaMensajes);
        listaMensajesView.setAdapter(adaptadorMensajes);

        final EditText txtMensaje = findViewById(R.id.txtMensaje);

        Mensajes mensajeIni = new Mensajes();
        mensajeIni.setIdMensaje(new Double(Math.random()).longValue());
        mensajeIni.setTextoMensaje("Hola Bienvenido al CHAT de Conderecho.org, \n ¿cual es tu consuta? y con gusto atendere tu solicitud");
        mensajeIni.setTipo(1);

        listaMensajes.add(mensajeIni);
        adaptadorMensajes.setListaMensajes(listaMensajes);
        listaMensajesView.setAdapter(adaptadorMensajes);
        listaMensajesView.setSelection(listaMensajes.size());

        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtMensaje.getText().toString().isEmpty()){
                    return;
                }
                Mensajes mensaje = new Mensajes();
                mensaje.setIdMensaje(new Double(Math.random()).longValue());
                mensaje.setTextoMensaje(txtMensaje.getText().toString());
                txtMensaje.setText("");
                mensaje.setTipo(2);
                listaMensajes.add(mensaje);



                Mensajes mensajeR = new Mensajes();
                mensajeR.setIdMensaje(new Double(Math.random()).longValue());
                mensajeR.setTextoMensaje("En este momento se presentan dificultades de conexion a nuestros servidores, por favor intente mas tarde. ");
                txtMensaje.setText("");
                mensajeR.setTipo(1);
                listaMensajes.add(mensajeR);
                adaptadorMensajes.setListaMensajes(listaMensajes);
                listaMensajesView.setAdapter(adaptadorMensajes);
                listaMensajesView.setSelection(listaMensajes.size());
            }
        });
    }
}
