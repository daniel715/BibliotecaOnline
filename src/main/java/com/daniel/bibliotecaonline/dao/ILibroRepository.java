package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Libro;

import java.util.Optional;

public interface ILibroRepository {

    Iterable<Libro> findAll();
    Libro findById(String id);
    Libro save(Libro libro);

    Libro updateLibro( String libroId ,Libro libro);
    void delete(String libroId);

    Iterable<Libro> buscarLibros(String param);

    Iterable<Libro> buscarporcategoria(String param);
}
