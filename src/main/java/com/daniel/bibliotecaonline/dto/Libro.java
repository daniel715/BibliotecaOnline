package com.daniel.bibliotecaonline.dto;

public class Libro {
    private String id;
    private String nombre;
    private String year;
    private int stock;
    private float precio;
    private String idAutor;
    private String imageurl;
    private String resumen;


    public Libro(String id, String nombre, String year, int stock, float precio, String idAutor, String imageurl, String resumen) {
        this.id = id;
        this.nombre = nombre;
        this.year = year;
        this.stock = stock;
        this.precio = precio;
        this.idAutor = idAutor;
        this.imageurl = imageurl;
        this.resumen = resumen;
    }

    public Libro() {
    }

    public String getResumen() {
        return resumen;
    }
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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


}
