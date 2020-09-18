package com.legal.fcp.conderechoapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.legal.fcp.conderechoapp.R;
import com.legal.fcp.conderechoapp.basedatos.vo.Palabra;
import com.legal.fcp.conderechoapp.basedatos.vo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPalabras extends BaseAdapter {

    public List<Palabra> getListaPalabras() {
        return listaPalabras;
    }

    public void setListaPalabras(List<Palabra> listaPalabras) {
        this.listaPalabras = listaPalabras;
    }

    private List<Palabra> listaPalabras;
    private List<Pregunta> listaPreguntas;
    Activity activity;
    private Boolean isPreguntas;

    public AdaptadorPalabras(Activity activity, List<Palabra> listaPalabras, ArrayList<Pregunta> listadoPreguntas, Boolean isPreguntas){
        this.setListaPalabras(listaPalabras);
        this.setListaPreguntas(listadoPreguntas);
        this.setPreguntas(isPreguntas);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return getListaPalabras().size();
    }

    @Override
    public Object getItem(int position) {
        if(getPreguntas()) {
            return this.getListaPreguntas().get(position);
        }else {
            return this.getListaPalabras().get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_palabra, null);
        }

        if (getPreguntas()){
            String pregunta = getListaPreguntas().get(i).getTextoPregunta();
            TextView textoMensje = v.findViewById(R.id.txtPalabra);
            textoMensje.setText(pregunta);
        }else{
            String palabra = getListaPalabras().get(i).getPalabraVisual();
            TextView textoMensje = v.findViewById(R.id.txtPalabra);
            textoMensje.setText(palabra);
        }



        return v;
    }

    public List<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    public void setListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    public Boolean getPreguntas() {
        return isPreguntas;
    }

    public void setPreguntas(Boolean preguntas) {
        isPreguntas = preguntas;
    }
}
