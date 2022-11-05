package com.daniel.bibliotecaonline.dto;

public class Categoria {
    private String id;
    private String nombre;

    public Categoria() {
    }

    public Categoria(String id, String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
