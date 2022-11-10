package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Libro;

import java.util.Optional;

public interface ILibroRepository {

    public Optional<Libro> findById(String id);

    public Iterable<Libro> findAll();

    public Optional<Libro> save(Optional<Libro> libro);

    public Optional<Libro> updateLibro( String libroId ,Optional<Libro> libro);

    public Optional<Libro> update(Optional<Libro> libro);

    public void delete(String libroId);

    public Iterable<Libro> buscarLibros(String param);

    public Iterable<Libro> buscarporcategoria(String param);
}
