package com.android.app.orquesta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {

    private String idPersona;
    private String Nombres;
    private String Apellidos;
    private String Dni;

    private String Genero;
    private String FechaNacimiento;
    private String Celular;
    private String Email;
    private String UserName;
    private String password;

    private String CodigoDepartamento;
    private String CodigoProvincia;
    private String CodigoDistrito;
    private String Foto;
    private String FechaRegistro;
    private String Rol;

    private String remember_token;
    private String created_at;
    private String updated_at;


    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
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

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }


    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodigoDepartamento() {
        return CodigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        CodigoDepartamento = codigoDepartamento;
    }

    public String getCodigoProvincia() {
        return CodigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        CodigoProvincia = codigoProvincia;
    }

    public String getCodigoDistrito() {
        return CodigoDistrito;
    }

    public void setCodigoDistrito(String codigoDistrito) {
        CodigoDistrito = codigoDistrito;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }


    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idPersona);
        dest.writeString(this.Nombres);
        dest.writeString(this.Apellidos);
        dest.writeString(this.Dni);
        dest.writeString(this.Genero);
        dest.writeString(this.FechaNacimiento);
        dest.writeString(this.Celular);
        dest.writeString(this.Email);
        dest.writeString(this.UserName);
        dest.writeString(this.password);
        dest.writeString(this.CodigoDepartamento);
        dest.writeString(this.CodigoProvincia);
        dest.writeString(this.CodigoDistrito);
        dest.writeString(this.Foto);
        dest.writeString(this.FechaRegistro);
        dest.writeString(this.remember_token);
        dest.writeString(this.created_at);
        dest.writeString(this.Rol);

    }

    public Persona() {
    }

    protected Persona(Parcel in) {
        this.idPersona = in.readString();
        this.Nombres = in.readString();
        this.Apellidos = in.readString();
        this.Dni = in.readString();
        this.Genero = in.readString();
        this.FechaNacimiento = in.readString();
        this.Celular = in.readString();
        this.Email = in.readString();
        this.UserName = in.readString();
        this.password = in.readString();
        this.CodigoDepartamento = in.readString();
        this.CodigoProvincia = in.readString();
        this.CodigoDistrito = in.readString();
        this.Foto =in.readString();
        this.FechaRegistro = in.readString();
        this.remember_token = in.readString();
        this.created_at = in.readString();
        this.Rol =in.readString();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel source) {
            return new Persona(source);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };
}
