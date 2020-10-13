package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Calificacion implements Parcelable {

    private String idCalificacion;
    private String idAgrupacion;
    private String idPersona;
    private String calificacion;
    private  String calTotal;

    public String getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(String idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getIdAgrupacion() {
        return idAgrupacion;
    }

    public void setIdAgrupacion(String idAgrupacion) {
        this.idAgrupacion = idAgrupacion;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getCalTotal() {
        return calTotal;
    }

    public void setCalTotal(String calTotal) {
        this.calTotal = calTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.idCalificacion);
        dest.writeString(this.idAgrupacion);
        dest.writeString(this.idPersona);
        dest.writeString(this.calificacion);
        dest.writeString(this.calTotal);

    }

    public Calificacion() {
    }

    protected Calificacion(Parcel in) {
        this.idCalificacion = in.readString();
        this.idAgrupacion = in.readString();
        this.idPersona = in.readString();
        this.calificacion = in.readString();
        this.calTotal = in.readString();
    }

    public static final Creator<Calificacion> CREATOR = new Creator<Calificacion>() {
        @Override
        public Calificacion createFromParcel(Parcel source) {
            return new Calificacion(source);
        }

        @Override
        public Calificacion[] newArray(int size) {
            return new Calificacion[size];
        }
    };
}
