package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Evento implements Parcelable {

    private int idEvento;
    private Date Fecha;
    private String FechaString;
    private String LocalDeEvento;
    private String DireccionLocal;

    private String TipoEvento;
    private String TipoEntrada;
    private String EstadoEvento;
    private String HoraInicio;
    private String HoraFin;

    public String getTipoEvento() {
        return TipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        TipoEvento = tipoEvento;
    }

    public String getTipoEntrada() {
        return TipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        TipoEntrada = tipoEntrada;
    }

    public String getEstadoEvento() {
        return EstadoEvento;
    }

    public void setEstadoEvento(String estadoEvento) {
        EstadoEvento = estadoEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }


    public void setLocalDeEvento(String LocalDeEvento) { this.LocalDeEvento = LocalDeEvento; }

    public void setDireccionLocal(String DireccionLocal) { this.DireccionLocal = DireccionLocal; }

    public void setHoraInicio (String HoraInicio) { this.HoraInicio = HoraInicio; }

    public void setHoraFin(String HoraFin) { this.HoraFin = HoraFin; }


    public int getIdEvento() {
        return idEvento;
    }



    public String getLocalDeEvento() { return LocalDeEvento; }

    public String getDireccionLocal() {
        return DireccionLocal;
    }



    public String getHoraInicio() {
        return HoraInicio;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getFechaString() {
        return FechaString;
    }

    public void setFechaString(String fechaString) {
        FechaString = fechaString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idEvento);
        dest.writeLong(this.Fecha != null ? this.Fecha.getTime() : -1);
        dest.writeString(this.FechaString);
        dest.writeString(this.LocalDeEvento);
        dest.writeString(this.DireccionLocal);
        dest.writeString(this.TipoEvento);
        dest.writeString(this.TipoEntrada);
        dest.writeString(this.EstadoEvento);
        dest.writeString(this.HoraInicio);
        dest.writeString(this.HoraFin);
    }

    public Evento() {
    }

    protected Evento(Parcel in) {
        this.idEvento = in.readInt();
        long tmpDate = in.readLong();
        this.Fecha = tmpDate == -1 ? null : new Date(tmpDate);
        this.FechaString = in.readString();
        this.LocalDeEvento = in.readString();
        this.DireccionLocal = in.readString();
        this.TipoEvento = in.readString();
        this.TipoEntrada = in.readString();
        this.EstadoEvento = in.readString();
        this.HoraInicio = in.readString();
        this.HoraFin =in.readString();
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel source) {
            return new Evento(source);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };
}
