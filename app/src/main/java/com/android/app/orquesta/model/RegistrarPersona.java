package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RegistrarPersona  implements Parcelable {

    private String nombres;
    private String apellidos;
    private String dni;
    private String celular;
    private String email;
    private String userName;
    private String password;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.nombres);
        dest.writeString(this.apellidos);
        dest.writeString(this.dni);
        dest.writeString(this.celular);
        dest.writeString(this.email);
        dest.writeString(this.userName);
        dest.writeString(this.password);


    }

    public RegistrarPersona() {
    }

    protected RegistrarPersona(Parcel in) {

        this.nombres = in.readString();
        this.apellidos = in.readString();
        this.dni = in.readString();
        this.celular = in.readString();
        this.email = in.readString();
        this.userName = in.readString();
        this.password = in.readString();

    }


    public static final Creator<RegistrarPersona> CREATOR = new Creator<RegistrarPersona>() {
        @Override
        public RegistrarPersona createFromParcel(Parcel source) {
            return new RegistrarPersona(source);
        }

        @Override
        public RegistrarPersona[] newArray(int size) {
            return new RegistrarPersona[size];
        }
    };
}
