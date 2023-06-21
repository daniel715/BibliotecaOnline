package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.IAutorRepository;
import com.daniel.bibliotecaonline.dto.Autor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AutorRepository implements IAutorRepository {


    private final JdbcTemplate jdbcTemplate;

    public AutorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Logger logger = Logger.getLogger(
            AutorRepository.class.getName());

    public List<Autor> findAll() {
        return jdbcTemplate.query("call Autor_list();", this::mapRowToAutor);
    }

    @Override
    public Autor findById(String autorId) {
        List<Autor> results = jdbcTemplate.query("call Autor_get_by_id(?)", this::mapRowToAutor, autorId);
        return results.size() == 0 ?
                null :
                results.get(0);
    }

    @Override
    public Autor save(Autor autor) {
        jdbcTemplate.update(
                "call Autor_save(?,?)",
                autor.getId(),
                autor.getNombre()
        );
        return autor;
    }

    @Override
    public Autor updateAutor(String autorId, Autor autor) {
        Autor autorToSend = this.findById(autorId);

        if (autorToSend == null) return null;
        else {
            if (autor.getNombre() != null) {
                autorToSend.setNombre(autor.getNombre());
            }

            String sqlquery = "call Autor_update(?,?)";
            jdbcTemplate.update(sqlquery, autorToSend.getId(), autorToSend.getNombre());
            return autor;
        }


    }

    //    queda pendiente arreglar buscarAutor
//    DELIMITER $$
//    DROP PROCEDURE IF EXISTS Autor_get_by_nombre$$
//    CREATE PROCEDURE Autor_get_by_nombre(IN nombre VARCHAR(20))
//    BEGIN
//    SELECT * FROM autor WHERE nombre LIKE '%h%';
//    End;
//    call Autor_get_by_nombre("H");
//    SELECT * FROM autor WHERE nombre LIKE '%h%';
    @Override
    public Iterable<Autor> buscarAutor(String param) {
        String sqlquery = "SELECT * FROM autor WHERE nombre LIKE '%" + param + "%'";
        return jdbcTemplate.query(sqlquery, this::mapRowToAutor);
    }

    @Override
    public void delete(String autorId) {
        String sqlquery = "call Autor_delete(?)";
        jdbcTemplate.update(sqlquery, autorId);
    }

    private Autor mapRowToAutor(ResultSet row, int rowNum) throws SQLException {
        return new Autor(row.getString("id_autor"), row.getString("nombre"));
    }
}
