package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.ICategoriaRepository;
import com.daniel.bibliotecaonline.dto.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements ICategoriaRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoriaRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Categoria> findById(String id) {
        List<Categoria> results = jdbcTemplate.query("select id, nombre from categoria where id=?", this::mapRowToCategoria, id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Iterable<Categoria> findAll() {
        return jdbcTemplate.query("select * from categoria", this::mapRowToCategoria);
    }

    @Override
    public Optional<Categoria> save(Optional<Categoria> categoria) {
        jdbcTemplate.update(
                "insert into categoria (id, nombre) values (?,?)",
                categoria.get().getId(),
                categoria.get().getNombre()
        );
        return categoria;
    }

    @Override
    public Optional<Categoria> updateCategoria(String categoriaId, Optional<Categoria> categoria) {
        Optional<Categoria> categoriaToSend = findById(categoriaId);
        if (categoria.get().getId() != null) {
            categoriaToSend.get().setId(categoria.get().getId());
        }
        if (categoria.get().getNombre() != null) {
            categoriaToSend.get().setNombre(categoria.get().getNombre());
        }
        return update(categoriaToSend);
    }

    @Override
    public Optional<Categoria> update(Optional<Categoria> categoria) {
        String sqlquery = "update categoria set nombre = ? where id= ?";
        jdbcTemplate.update(sqlquery,
                categoria.get().getNombre(),
                categoria.get().getId()
        );
        return categoria;
    }

    @Override
    public Iterable<Categoria> buscarCategoria(String param) {
        String sqlquery = "SELECT * FROM librocategorias WHERE nombre LIKE '%" + param + "%'";
        return jdbcTemplate.query(sqlquery, this::mapRowToCategoria);
    }

    @Override
    public void delete(String categoriaId) {
        String sqlquery = "DELETE FROM categoria where id = ?";
        jdbcTemplate.update(sqlquery, categoriaId);
    }

    private Categoria mapRowToCategoria(ResultSet row, int rowNum)
            throws SQLException {
        return new Categoria(
                row.getString("id"),
                row.getString("nombre")
        );
    }
}
