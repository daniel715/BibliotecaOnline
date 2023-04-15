package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Categoria;

import java.util.Optional;

public interface ICategoriaRepository {
    Optional<Categoria> findById(String id);

    Iterable<Categoria> findAll();

    Optional<Categoria> save(Optional<Categoria> autor);

    Optional<Categoria> updateCategoria(String autorId , Optional<Categoria> autor);

    Optional<Categoria> update(Optional<Categoria> autor);

    Iterable<Categoria> buscarCategoria(String param);

    void delete(String autorId);
}
