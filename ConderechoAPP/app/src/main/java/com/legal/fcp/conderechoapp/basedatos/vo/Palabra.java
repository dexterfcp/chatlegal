package com.legal.fcp.conderechoapp.basedatos.vo;

import java.io.Serializable;

public class Palabra implements Serializable {


    private String palabraVisual;
    private String significado;
    private int version;
    private int estado;


    public String getPalabraVisual() {
        return palabraVisual;
    }

    public void setPalabraVisual(String palabraVisual) {
        this.palabraVisual = palabraVisual;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
