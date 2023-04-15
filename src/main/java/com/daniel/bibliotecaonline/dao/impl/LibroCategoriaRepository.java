package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.ILibroCategoriaRepository;
import com.daniel.bibliotecaonline.dto.LibroCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LibroCategoriaRepository implements ILibroCategoriaRepository {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public LibroCategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<LibroCategoria> save(Optional<LibroCategoria> libroCategoria) {
        jdbcTemplate.update(
                "insert into librocategorias (idLibro,categorias) values (?,?)",
                libroCategoria.get().getLibroId(),
                libroCategoria.get().getCategoriasId()
        );
        return libroCategoria;
    }
}
