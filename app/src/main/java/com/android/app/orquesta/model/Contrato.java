package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contrato implements Parcelable {

    private int idContrato;
    private String RazonSocial;
    private String LocalDeEvento;
    private String DireccionLocal;

    private String Nombres;
    private String Apellidos;
    private String EstadoEvento;

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getLocalDeEvento() {
        return LocalDeEvento;
    }

    public void setLocalDeEvento(String localDeEvento) {
        LocalDeEvento = localDeEvento;
    }

    public String getDireccionLocal() {
        return DireccionLocal;
    }

    public void setDireccionLocal(String direccionLocal) {
        DireccionLocal = direccionLocal;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getEstadoEvento() {
        return EstadoEvento;
    }

    public void setEstadoEvento(String estadoEvento) {
        EstadoEvento = estadoEvento;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idContrato);
        dest.writeString(this.RazonSocial);
        dest.writeString(this.LocalDeEvento);
        dest.writeString(this.DireccionLocal);
        dest.writeString(this.Nombres);
        dest.writeString(this.Apellidos);
        dest.writeString(this.EstadoEvento);

    }

    public Contrato() {
    }

    protected Contrato(Parcel in) {
        this.idContrato = in.readInt();
        this.RazonSocial = in.readString();
        this.LocalDeEvento = in.readString();
        this.DireccionLocal = in.readString();
        this.Nombres = in.readString();
        this.Apellidos = in.readString();
        this.EstadoEvento = in.readString();

    }

    public static final Creator<Contrato> CREATOR = new Creator<Contrato>() {
        @Override
        public Contrato createFromParcel(Parcel source) {
            return new Contrato(source);
        }

        @Override
        public Contrato[] newArray(int size) {
            return new Contrato[size];
        }
    };
}
