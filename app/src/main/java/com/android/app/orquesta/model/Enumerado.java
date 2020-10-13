package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Enumerado implements Parcelable {

    private int idEnumerado;
    private String Nombre;
    private String Valor_enumerado;
    private String Tipo_Enumerado;
    private String Estado_Enumerado;

    public int getIdEnumerado() {
        return idEnumerado;
    }

    public void setIdEnumerado(int idEnumerado) {
        this.idEnumerado = idEnumerado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getValor_enumerado() {
        return Valor_enumerado;
    }

    public void setValor_enumerado(String valor_enumerado) {
        Valor_enumerado = valor_enumerado;
    }

    public String getTipo_Enumerado() {
        return Tipo_Enumerado;
    }

    public void setTipo_Enumerado(String tipo_Enumerado) {
        Tipo_Enumerado = tipo_Enumerado;
    }

    public String getEstado_Enumerado() {
        return Estado_Enumerado;
    }

    public void setEstado_Enumerado(String estado_Enumerado) {
        Estado_Enumerado = estado_Enumerado;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idEnumerado);
        dest.writeString(this.Nombre);
        dest.writeString(this.Valor_enumerado);
        dest.writeString(this.Tipo_Enumerado);
        dest.writeString(this.Estado_Enumerado);
    }

    public Enumerado() {
    }

    protected Enumerado(Parcel in) {
        this.idEnumerado = in.readInt();
        this.Nombre = in.readString();
        this.Valor_enumerado = in.readString();
        this.Tipo_Enumerado = in.readString();
        this.Estado_Enumerado = in.readString();
    }

    public static final Creator<Enumerado> CREATOR = new Creator<Enumerado>() {
        @Override
        public Enumerado createFromParcel(Parcel source) {
            return new Enumerado(source);
        }

        @Override
        public Enumerado[] newArray(int size) {
            return new Enumerado[size];
        }
    };


}
