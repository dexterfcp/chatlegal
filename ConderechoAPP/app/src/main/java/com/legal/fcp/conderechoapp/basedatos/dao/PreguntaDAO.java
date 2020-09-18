package com.legal.fcp.conderechoapp.basedatos.dao;

import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.legal.fcp.conderechoapp.adapters.AdaptadorPalabras;
import com.legal.fcp.conderechoapp.basedatos.vo.Palabra;
import com.legal.fcp.conderechoapp.basedatos.vo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAO {

    private DatabaseReference cnn;
    private FirebaseFirestore dbf;

    public PreguntaDAO(DatabaseReference cc){
        cnn = cc.child("preguntasfrec");
    }

    public PreguntaDAO(FirebaseFirestore dbf){
        this.dbf = dbf;
    }

    public void buscarPalabra(String palabra, final AdaptadorPalabras adaptador, final ListView listView){
        List<Pregunta> listadoPalabras = new ArrayList<>();
        if(cnn != null) {
            cnn.addChildEventListener(getChildEventListener(palabra, adaptador, listView, listadoPalabras));
        }
        if(dbf != null){

        }
    }

    public ChildEventListener getChildEventListener(final String palabra, final AdaptadorPalabras adaptador, final ListView listView, final List listado){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dsItem, @Nullable String s) {

                if (dsItem.exists()&& ((!palabra.isEmpty() && dsItem.child("palabraVisual").getValue().toString().contains(palabra.toUpperCase())) || palabra.isEmpty())){
                    Pregunta p = new Pregunta();
                    p.setTextoPregunta(dsItem.child("textopregunta").getValue().toString());
                    p.setRespuestaPregunta(dsItem.child("respuesta").getValue().toString());
                    p.setEstado(Integer.parseInt(dsItem.child("estado").getValue().toString()));
                    p.setVersion(Integer.parseInt(dsItem.child("version").getValue().toString()));
                    listado.add(p);
                    adaptador.setListaPalabras(listado);
                    listView.setAdapter(adaptador);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println("CAMBIO");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("REMOV");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println("MOVED");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.printf("CANCEL");
            }
        };
    }



}
