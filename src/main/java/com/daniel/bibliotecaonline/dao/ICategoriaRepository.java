package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Categoria;

import java.util.Optional;

public interface ICategoriaRepository {
    Categoria findById(String id);

    Iterable<Categoria> findAll();

    Categoria save(Categoria autor);

    Categoria updateCategoria(String autorId , Categoria autor);

    Iterable<Categoria> buscarCategoria(String param);

    void delete(String autorId);
}
