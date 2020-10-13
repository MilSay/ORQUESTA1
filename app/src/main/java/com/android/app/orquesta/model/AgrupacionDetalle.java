package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AgrupacionDetalle implements Parcelable {

    private int idDetalleAgrupacion;
    private String Foto;
    private String Nombres;
    private String Apellidos;
    private String TipoPersona;

    private String Celular;
    private String facebook;
    private String twitter;
    private String youtuve;

    private String Email;
    private String Direccion;

    public int getIdDetalleAgrupacion() {
        return idDetalleAgrupacion;
    }

    public void setIdDetalleAgrupacion(int idDetalleAgrupacion) {
        this.idDetalleAgrupacion = idDetalleAgrupacion;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
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

    public String getTipoPersona() {
        return TipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        TipoPersona = tipoPersona;
    }


    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutuve() {
        return youtuve;
    }

    public void setYoutuve(String youtuve) {
        this.youtuve = youtuve;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idDetalleAgrupacion);
        dest.writeString(this.Nombres);
        dest.writeString(this.Apellidos);
        dest.writeString(this.TipoPersona);
        dest.writeString(this.Foto);

        dest.writeString(this.Celular);
        dest.writeString(this.facebook);
        dest.writeString(this.twitter);
        dest.writeString(this.youtuve);

        dest.writeString(this.Email);
        dest.writeString(this.Direccion);


    }

    public AgrupacionDetalle() {
    }

    protected AgrupacionDetalle(Parcel in) {
        this.idDetalleAgrupacion = in.readInt();
        this.Nombres = in.readString();
        this.Apellidos = in.readString();
        this.TipoPersona = in.readString();
        this.Foto =in.readString();

        this.Celular = in.readString();
        this.facebook = in.readString();
        this.twitter =in.readString();
        this.youtuve =in.readString();

        this.Email =in.readString();
        this.Direccion =in.readString();
    }

    public static final Creator<AgrupacionDetalle> CREATOR = new Creator<AgrupacionDetalle>() {
        @Override
        public AgrupacionDetalle createFromParcel(Parcel source) {
            return new AgrupacionDetalle(source);
        }

        @Override
        public AgrupacionDetalle[] newArray(int size) {
            return new AgrupacionDetalle[size];
        }
    };
}
