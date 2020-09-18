package com.legal.fcp.conderechoapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.legal.fcp.conderechoapp.R;
import com.legal.fcp.conderechoapp.objetos.Mensajes;

import java.util.List;

public class AdaptadorMensajes extends BaseAdapter {

    private List<Mensajes> listaMensajes;
    Activity activity;

    public AdaptadorMensajes(Activity activity, List<Mensajes> listaMensajes){
        this.activity = activity;
        this.setListaMensajes(listaMensajes);
    }

    @Override
    public int getCount() {
        return getListaMensajes().size();
    }

    @Override
    public Object getItem(int i) {
        return this.getListaMensajes().get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.getListaMensajes().get(i).getIdMensaje();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

      View v = view;

        if (v == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_mensaje, null);
        }

        Mensajes mensaje = getListaMensajes().get(i);
        TextView textoMensje = v.findViewById(R.id.textMensaje);
        textoMensje.setText(mensaje.getTextoMensaje());
        if(mensaje.getTipo() ==1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ((LinearLayout)v.findViewById(R.id.contenedorMensaje)).setGravity(Gravity.LEFT);
                textoMensje.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                textoMensje.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimaryDark));
                textoMensje.setTextColor(activity.getResources().getColor(R.color.colorNegro));
            }
        }
        return v;
    }

    public List<Mensajes> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensajes> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
}
