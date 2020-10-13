package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Videos implements Parcelable {

    private int idVideos;
    private int idAgrupacion;
    private String link;
    private String Descripcion;
    private String FechaRegistro;

    public int getIdVideos() {
        return idVideos;
    }

    public void setIdVideos(int idVideos) {
        this.idVideos = idVideos;
    }

    public int getIdAgrupacion() {
        return idAgrupacion;
    }

    public void setIdAgrupacion(int idAgrupacion) {
        this.idAgrupacion = idAgrupacion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }


    public Videos(String videoUrl,String descripcion) {
        this.link = videoUrl;
        this.Descripcion=descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idVideos);
        dest.writeInt(this.idAgrupacion);
        dest.writeString(this.link);
        dest.writeString(this.Descripcion);
        dest.writeString(this.FechaRegistro);
    }

    public Videos() {
    }

    protected Videos(Parcel in) {
        this.idVideos = in.readInt();
        this.idAgrupacion = in.readInt();
        this.link = in.readString();
        this.Descripcion = in.readString();
        this.FechaRegistro = in.readString();
    }

    public static final Creator<Videos> CREATOR = new Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel source) {
            return new Videos(source);
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };
}
