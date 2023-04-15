package com.daniel.bibliotecaonline.dto;

public class LibroCategoria {
    private String libroId;
    private String categoriasId;

    public LibroCategoria(String libroId, String categoriasId) {
        this.libroId = libroId;
        this.categoriasId = categoriasId;
    }

    public LibroCategoria() {
    }

    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public String getCategoriasId() {
        return categoriasId;
    }

    public void setCategoriasId(String categoriasId) {
        this.categoriasId = categoriasId;
    }
}
