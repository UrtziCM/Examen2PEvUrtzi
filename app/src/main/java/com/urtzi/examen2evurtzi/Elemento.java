package com.urtzi.examen2evurtzi;

import androidx.annotation.NonNull;

public class Elemento {
    private String nombre;
    private String simbolo;
    private String numero;
    private String estado;

    public Elemento(String nombre, String simbolo, String numero, String estado) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.numero = numero;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre + "\n" + simbolo + "\n" + numero + "\n" +  estado;
    }
}
