package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Categoria;

import java.util.Optional;

public interface ICategoriaRepository {
    public Optional<Categoria> findById(String id);

    public Iterable<Categoria> findAll();

    public Optional<Categoria> save(Optional<Categoria> autor);

    public Optional<Categoria> updateCategoria(String autorId , Optional<Categoria> autor);

    public Optional<Categoria> update(Optional<Categoria> autor);

    public Iterable<Categoria> buscarCategoria(String param);

    public void delete(String autorId);
}
