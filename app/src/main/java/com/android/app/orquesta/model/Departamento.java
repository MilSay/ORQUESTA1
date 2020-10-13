package com.android.app.orquesta.model;

public class Departamento {
    public String cod_depa;
    public String departamento;

    public Departamento() {
    }

    public Departamento(String cod_depa, String nomDepartamento) {
        this.cod_depa = cod_depa;
        this.departamento = nomDepartamento;
    }

    public String getCod_depa() {
        return cod_depa;
    }

    public void setCod_depa(String cod_depa) {
        this.cod_depa = cod_depa;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
