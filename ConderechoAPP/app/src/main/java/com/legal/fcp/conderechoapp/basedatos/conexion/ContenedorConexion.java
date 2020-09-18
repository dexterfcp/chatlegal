package com.legal.fcp.conderechoapp.basedatos.conexion;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class ContenedorConexion {

    private static DatabaseReference dbConderecho;
    private static FirebaseFirestore dbf ;

    public static DatabaseReference getDbConderecho2() {
        if(dbConderecho == null){
            dbConderecho = FirebaseDatabase.getInstance().getReference("conderecho");
        }
        return dbConderecho;
    }

    public static FirebaseFirestore getDbConderecho() {
        if(dbf == null){
            dbf =FirebaseFirestore.getInstance();
        }
        return dbf;
    }
}
