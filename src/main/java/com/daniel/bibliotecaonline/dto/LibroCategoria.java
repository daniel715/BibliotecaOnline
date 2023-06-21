package com.daniel.bibliotecaonline.dto;

public class LibroCategoria {
    private String idLibro;
    private String idCategoria;

    public LibroCategoria(String idLibro, String idCategoria) {
        this.idLibro = idLibro;
        this.idCategoria = idCategoria;
    }

    public LibroCategoria() {

    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}
