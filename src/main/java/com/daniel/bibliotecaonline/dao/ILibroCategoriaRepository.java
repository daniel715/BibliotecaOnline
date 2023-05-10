package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Libro;
import com.daniel.bibliotecaonline.dto.LibroCategoria;

import java.util.Optional;

public interface ILibroCategoriaRepository {

    Optional<LibroCategoria> save(Optional<LibroCategoria> libroCategoria);


    Optional<LibroCategoria> findCategoriasByLibroId(String id);

    void delete(String id);

    Optional<LibroCategoria> updateLibroCategoria(String libroId, Optional<LibroCategoria> libro);
    Optional<LibroCategoria> update(Optional<LibroCategoria> libro, String libroId);
}
