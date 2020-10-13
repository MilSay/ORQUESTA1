package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Agrupacion implements Parcelable {

    private int idAgrupacion;
    private String RazonSocial;
    private String Ruc;
    private String FechaCreacion;

    private String Historia;
    private String CodigoDepartamento;
    private String CodigoProvincia;
    private String CodigoDistrito;
    private String GeneroMusical;
    private String Foto;

    private float Calificacion;

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getGeneroMusical() {
        return GeneroMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        GeneroMusical = generoMusical;
    }

    public void setIdAgrupacion(int idAgrupacion) {
        this.idAgrupacion = idAgrupacion;
    }

    public void setRazonSocial(String RazonSocial) { this.RazonSocial = RazonSocial; }

    public void setRuc(String Ruc) { this.Ruc = Ruc; }

    public void setFechaCreacion(String FechaCreacion) { this.FechaCreacion = FechaCreacion; }

    public void setHistoria(String Historia) { this.Historia = Historia; }

    public void setCodigoDepartamento(String CodigoDepartamento) { this.CodigoDepartamento = CodigoDepartamento; }

    public void setCodigoProvincia(String CodigoProvincia) { this.CodigoProvincia = CodigoProvincia; }

    public void setCodigoDistrito (String CodigoDistrito) { this.CodigoDistrito = CodigoDistrito; }




    public int getIdAgrupacion() {
        return idAgrupacion;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public String getRuc() { return Ruc; }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public String getHistoria() {
        return Historia;
    }

    public String getCodigoDepartamento() {
        return CodigoDepartamento;
    }

    public String getCodigoProvincia() {
        return CodigoProvincia;
    }

    public String getCodigoDistrito() {
        return CodigoDistrito;
    }

    public float getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(float calificacion) {
        Calificacion = calificacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idAgrupacion);
        dest.writeString(this.RazonSocial);
        dest.writeString(this.Ruc);
        dest.writeString(this.FechaCreacion);
        dest.writeString(this.Historia);
        dest.writeString(this.CodigoDepartamento);
        dest.writeString(this.CodigoProvincia);
        dest.writeString(this.CodigoDistrito);
        dest.writeString(this.GeneroMusical);
        dest.writeString(this.Foto);
        dest.writeFloat(this.Calificacion);
    }

    public Agrupacion() {
    }

    protected Agrupacion(Parcel in) {
        this.idAgrupacion = in.readInt();
        this.RazonSocial = in.readString();
        this.Ruc = in.readString();
        this.FechaCreacion = in.readString();
        this.Historia = in.readString();
        this.CodigoDepartamento = in.readString();
        this.CodigoProvincia = in.readString();
        this.CodigoDistrito = in.readString();
        this.GeneroMusical =in.readString();
        this.Foto =in.readString();
        this.Calificacion =in.readFloat();
    }

    public static final Creator<Agrupacion> CREATOR = new Creator<Agrupacion>() {
        @Override
        public Agrupacion createFromParcel(Parcel source) {
            return new Agrupacion(source);
        }

        @Override
        public Agrupacion[] newArray(int size) {
            return new Agrupacion[size];
        }
    };
}
