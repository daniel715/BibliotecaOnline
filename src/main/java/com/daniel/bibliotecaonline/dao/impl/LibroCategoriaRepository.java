package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.ILibroCategoriaRepository;
import com.daniel.bibliotecaonline.dto.Libro;
import com.daniel.bibliotecaonline.dto.LibroCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LibroCategoriaRepository implements ILibroCategoriaRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LibroCategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<LibroCategoria> findAll() {
        return jdbcTemplate.query("call libro_categoria_list()", this::mapRowToLibroCategoria);
    }

    @Override
    public LibroCategoria save(LibroCategoria libroCategoria) {
        jdbcTemplate.update(
                "call libro_categoria_save(?,?)",
                libroCategoria.getIdLibro(),
                libroCategoria.getIdCategoria());
        return libroCategoria;
    }

    @Override
    public void update(String idLibro, String categorias) {
        String sqlquery = "call libro_categoria_update(?, ?)";

        jdbcTemplate.update(sqlquery,
                idLibro,
                categorias
        );
    }

    @Override
    public void delete(String libroId) {
        String sqlquery = "call libro_categoria_delete(?)";
        jdbcTemplate.update(sqlquery, libroId);
    }

    @Override
    public List<LibroCategoria> findById(String id) {
        return jdbcTemplate.query("call libro_categoria_get_by_id(?);", this::mapRowToLibroCategoria, id);
    }

    private LibroCategoria mapRowToLibroCategoria(ResultSet row, int rowNum)
            throws SQLException {
        return new LibroCategoria(
                row.getString("id_libro"),
                row.getString("id_categoria"));
    }
}
