package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.ILibroCategoriaRepository;
import com.daniel.bibliotecaonline.dto.Autor;
import com.daniel.bibliotecaonline.dto.LibroCategoria;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
                "insert into librocategorias (idLibro,categoriasId, categorias) values (?,?,?)",
                libroCategoria.get().getLibroId(),
                libroCategoria.get().getCategoriasId(),
                libroCategoria.get().getCategorias()
        );
        return libroCategoria;
    }

    @Override
    public Optional<LibroCategoria> findCategoriasByLibroId(String id) {
        List<LibroCategoria> results = jdbcTemplate.query("select * from librocategorias where idLibro=?", this::mapRowToLibroCategoria, id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public void delete(String libroId) {
        String sqlquery = "DELETE FROM librocategorias where idLibro = ?";
        jdbcTemplate.update(sqlquery, libroId);
    }

    @Override
    public Optional<LibroCategoria> updateLibroCategoria(String libroId, Optional<LibroCategoria> libro) {
        Optional<LibroCategoria> libroToSend = findCategoriasByLibroId(libroId);
        if (libro.get().getCategoriasId().length()  > 0) {
            libroToSend.get().setCategoriasId(libro.get().getCategoriasId());
        }
        if (libro.get().getCategorias().length() > 0) {
            libroToSend.get().setCategorias(libro.get().getCategorias());
        }
        return update(libroToSend, libroId);
    }

    @Override
    public Optional<LibroCategoria> update(Optional<LibroCategoria> libro, String libroId) {
        String sqlquery = "update librocategorias set categoriasId = ?, categorias = ? where idLibro= ?";

        jdbcTemplate.update(sqlquery,
                libro.get().getCategoriasId(),
                libro.get().getCategorias(),
                libroId
        );
        return libro;
    }

    @Override
    public Iterable<LibroCategoria> findAll() {
        return jdbcTemplate.query("select * from librocategorias", this::mapRowToLibroCategoria);
    }

    private LibroCategoria mapRowToLibroCategoria(ResultSet row, int rowNum)
            throws SQLException {
        return new LibroCategoria(
                row.getString("idLibro"),
                row.getString("categoriasId"),
                row.getString("categorias")
        );
    }
}
