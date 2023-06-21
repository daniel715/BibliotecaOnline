package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Autor;

import java.util.List;

public interface IAutorRepository {

    Autor findById(String id);

    List<Autor> findAll();

    Autor save(Autor autor);

    Autor updateAutor(String autorId, Autor autor);

    Iterable<Autor> buscarAutor(String param);

    void delete(String autorId);
}
