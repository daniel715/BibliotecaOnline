package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Autor;

import java.util.Optional;

public interface IAutorRepository {

    Optional<Autor> findById(String id);

    Iterable<Autor> findAll();

    Optional<Autor> save (Optional<Autor> autor);

    Optional<Autor> updateAutor(String autorId , Optional<Autor> autor);

    Optional<Autor> update(Optional<Autor> autor);

    Iterable<Autor> buscarAutor(String param);

    void delete(String autorId);
}
