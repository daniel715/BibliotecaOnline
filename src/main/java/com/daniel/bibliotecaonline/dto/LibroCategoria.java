package com.daniel.bibliotecaonline.dto;

public class LibroCategoria {
    private String libroId;
    private String categoriasId;

    private String categorias;



    public LibroCategoria(String libroId, String categoriasId, String categorias) {
        this.libroId = libroId;
        this.categoriasId = categoriasId;
        this.categorias = categorias;
    }

    public LibroCategoria() {
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
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
