package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.LibroCategoria;

import java.util.Optional;

public interface ILibroCategoriaRepository {

    Optional<LibroCategoria> save(Optional<LibroCategoria> libroCategoria);


}
