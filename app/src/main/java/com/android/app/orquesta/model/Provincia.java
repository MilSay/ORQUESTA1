package com.android.app.orquesta.model;

public class Provincia {
    public String cod_prov;
    public String provincia;

    public Provincia() {
    }

    public Provincia(String cod_prov, String provincia) {
        this.cod_prov = cod_prov;
        this.provincia = provincia;
    }

    public String getCod_prov() {
        return cod_prov;
    }

    public void setCod_prov(String cod_prov) {
        this.cod_prov = cod_prov;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
