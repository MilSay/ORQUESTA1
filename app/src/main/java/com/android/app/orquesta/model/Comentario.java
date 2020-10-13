package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Comentario implements Parcelable {

    private String idPersona;
    private String Comentario;
    private String FechaRegistro;
    private String idAgrupacion;

    private String Foto;
    private String Apellidos;
    private String Nombres;


    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdAgrupacion() {
        return idAgrupacion;
    }

    public void setIdAgrupacion(String idAgrupacion) {
        this.idAgrupacion = idAgrupacion;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idPersona);
        dest.writeString(this.Comentario);
        dest.writeString(this.FechaRegistro);
        dest.writeString(this.idAgrupacion);
    }

    public Comentario() {
    }

    protected Comentario(Parcel in) {
        this.idPersona = in.readString();
        this.Comentario = in.readString();
        this.FechaRegistro = in.readString();
        this.idAgrupacion = in.readString();
    }

    public static final Creator<Comentario> CREATOR = new Creator<Comentario>() {
        @Override
        public Comentario createFromParcel(Parcel source) {
            return new Comentario(source);
        }

        @Override
        public Comentario[] newArray(int size) {
            return new Comentario[size];
        }
    };
}
