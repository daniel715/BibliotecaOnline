package com.daniel.bibliotecaonline.dto;

public class Libro {
    private String idLibro;
    private String nombre;
    private String year;
    private float precio;
    private String idAutor;
    private String resumen;
    private Boolean isSelled;
    private String idPedido;


    public Libro(String idLibro, String nombre, String year, float precio, String idAutor, String resumen, Boolean isSelled, String idPedido) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.year = year;
        this.precio = precio;
        this.idAutor = idAutor;
        this.resumen = resumen;
        this.isSelled = isSelled;
        this.idPedido = idPedido;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Boolean getSelled() {
        return isSelled;
    }

    public void setSelled(Boolean selled) {
        isSelled = selled;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }
}
