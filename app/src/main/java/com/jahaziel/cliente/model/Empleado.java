package com.jahaziel.cliente.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empleado {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("telefono")
    @Expose
    private String telefono;

    @SerializedName("correo")
    @Expose
    private String correo;

    @SerializedName("direccion")
    @Expose
    private String direccion;

    public Empleado() { }

    public Empleado(Long id, String nombre, String telefono, String correo, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
