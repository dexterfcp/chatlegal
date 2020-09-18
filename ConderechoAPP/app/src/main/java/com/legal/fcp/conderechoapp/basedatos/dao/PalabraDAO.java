package com.legal.fcp.conderechoapp.basedatos.dao;

import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.legal.fcp.conderechoapp.adapters.AdaptadorPalabras;
import com.legal.fcp.conderechoapp.basedatos.vo.Palabra;

import java.util.ArrayList;
import java.util.List;

public class PalabraDAO {

    private DatabaseReference cnn;
    private FirebaseFirestore dbf;

    public PalabraDAO(DatabaseReference cc){
        cnn = cc.child("palabras");
    }

    public PalabraDAO(FirebaseFirestore dbf){
        this.dbf = dbf;
    }


    public void buscarPalabra(String palabra, final AdaptadorPalabras adaptador, final ListView listView){
        final List<Palabra> listadoPalabras = new ArrayList<>();
        if (cnn != null) {
            cnn.addChildEventListener(getChildEventListener(palabra, adaptador, listView, listadoPalabras));
        }
        if(dbf != null){
            Query query = dbf.collection("palabras");
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listadoPalabras.add(document.toObject(Palabra.class));
                            }
                            adaptador.setListaPalabras(listadoPalabras);
                            listView.setAdapter(adaptador);
                        }
                    }
                }
            });
        }
    }

    public ChildEventListener getChildEventListener(final String palabra, final AdaptadorPalabras adaptador, final ListView listView, final List<Palabra> listadoPalabras){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dsItem, @Nullable String s) {

                if (dsItem.exists()&& ((!palabra.isEmpty() && dsItem.child("palabraVisual").getValue().toString().contains(palabra.toUpperCase())) || palabra.isEmpty())){
                    Palabra p = new Palabra();
                    p.setPalabraVisual(dsItem.child("palabraVisual").getValue().toString());
                    p.setSignificado(dsItem.child("significado").getValue().toString());
                    p.setEstado(Integer.parseInt(dsItem.child("estado").getValue().toString()));
                    p.setVersion(Integer.parseInt(dsItem.child("version").getValue().toString()));
                    listadoPalabras.add(p);
                    adaptador.setListaPalabras(listadoPalabras);
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
