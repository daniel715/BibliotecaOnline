package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Libro;

import java.util.Optional;

public interface ILibroRepository {

    Optional<Libro> findById(String id);

    Iterable<Libro> findAll();

    Optional<Libro> save(Optional<Libro> libro);

    Optional<Libro> updateLibro( String libroId ,Optional<Libro> libro);

    Optional<Libro> update(Optional<Libro> libro);

    void delete(String libroId);

    Iterable<Libro> buscarLibros(String param);

    Iterable<Libro> buscarporcategoria(String param);
}
