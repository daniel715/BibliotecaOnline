package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.LibroCategoria;

public interface ILibroCategoriaRepository {

    Iterable<LibroCategoria> findAll();

    LibroCategoria save(LibroCategoria libroCategoria);

    void update(String libroId, String categorias);

    void delete(String libroId);
}
