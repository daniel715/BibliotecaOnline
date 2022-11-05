package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.IAutorRepository;
import com.daniel.bibliotecaonline.dto.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AutorRepository implements IAutorRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AutorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Autor> findById(String id) {
        List<Autor> results = jdbcTemplate.query("select id, nombre from Autor where id=?", this::mapRowToAutor, id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Iterable<Autor> findAll() {
        return jdbcTemplate.query("select * from autor", this::mapRowToAutor);
    }

    @Override
    public Optional<Autor> save(Optional<Autor> autor) {
        jdbcTemplate.update(
                "insert into autor (id, nombre) values (?,?)",
                autor.get().getId(),
                autor.get().getNombre()
        );
        return autor;
    }

    @Override
    public Optional<Autor> updateAutor(String autorId, Optional<Autor> autor) {
        Optional<Autor> autorToSend = findById(autorId);
        if (autor.get().getId() != null) {
            autorToSend.get().setId(autor.get().getId());
        }
        if (autor.get().getNombre() != null) {
            autorToSend.get().setNombre(autor.get().getNombre());
        }
        return update(autorToSend);
    }

    @Override
    public Optional<Autor> update(Optional<Autor> autor) {
        String sqlquery = "update Autor set nombre = ? where id= ?";
        jdbcTemplate.update(sqlquery,
                autor.get().getNombre(),
                autor.get().getId()
        );
        return autor;
    }

    @Override
    public Iterable<Autor> buscarAutor(String param) {
        String sqlquery = "SELECT * FROM autor WHERE nombre LIKE '%" + param + "%'";
        return jdbcTemplate.query(sqlquery, this::mapRowToAutor);
    }

    @Override
    public void delete(String autorId) {
        String sqlquery = "DELETE FROM autor where id = ?";
        jdbcTemplate.update(sqlquery, autorId);
    }

    private Autor mapRowToAutor(ResultSet row, int rowNum)
            throws SQLException {
        return new Autor(
                row.getString("id"),
                row.getString("nombre")
        );
    }
}
