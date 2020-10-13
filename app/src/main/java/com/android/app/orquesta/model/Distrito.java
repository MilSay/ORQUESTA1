package com.android.app.orquesta.model;

public class Distrito {
    public String cod_dist;
    public String distrito;
    public Distrito() {
    }

    public Distrito(String cod_dist, String distrito) {
        this.cod_dist = cod_dist;
        this.distrito = distrito;
    }

    public String getCod_dist() {
        return cod_dist;
    }

    public void setCod_dist(String cod_dist) {
        this.cod_dist = cod_dist;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
}
