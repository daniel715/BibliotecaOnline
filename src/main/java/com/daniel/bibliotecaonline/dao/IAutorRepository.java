package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Autor;

import java.util.Optional;

public interface IAutorRepository {

    public Optional<Autor> findById(String id);

    public Iterable<Autor> findAll();

    public Optional<Autor> save (Optional<Autor> autor);

    public Optional<Autor> updateAutor(String autorId , Optional<Autor> autor);

    public Optional<Autor> update(Optional<Autor> autor);

    public Iterable<Autor> buscarAutor(String param);

    public void delete(String autorId);
}
