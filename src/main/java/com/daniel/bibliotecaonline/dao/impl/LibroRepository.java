package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.ILibroRepository;
import com.daniel.bibliotecaonline.dto.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class LibroRepository implements ILibroRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LibroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Libro> findById(String id) {
        List<Libro> results = jdbcTemplate.query("select id, nombre,  year, stock,precio,Autor_id,imageurl from Libro where id=?", this::mapRowToLibro, id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Iterable<Libro> findAll() {
        return jdbcTemplate.query("select id, nombre,year, stock,precio,Autor_id,imageurl from Libro", this::mapRowToLibro);
    }

    private Libro mapRowToLibro(ResultSet row, int rowNum)
            throws SQLException {
        return new Libro(
                row.getString("id"),
                row.getString("nombre"),
                row.getString("year"),
                row.getInt("stock"),
                row.getFloat("precio"),
                row.getString("Autor_id"),
                row.getString("imageurl")
        );
    }

    @Override
    public Optional<Libro> save(Optional<Libro> libro) {
        jdbcTemplate.update(
                "insert into Libro (id, nombre, year, stock, precio, Autor_id, imageurl) values (?,?,?,?,?,?,?)",
                libro.get().getId(),
                libro.get().getNombre(),
                libro.get().getYear(),
                libro.get().getStock(),
                libro.get().getPrecio(),
                libro.get().getIdAutor(),
                libro.get().getImageurl()
        );
        return libro;
    }

    @Override
    public Optional<Libro> updateLibro(String libroId, Optional<Libro> libro) {
        Optional<Libro> libroToSend = findById(libroId);
        if (libro.get().getNombre() != null) {
            libroToSend.get().setNombre(libro.get().getNombre());
        }
        if (libro.get().getYear() != null) {
            libroToSend.get().setYear(libro.get().getYear());
        }
        if (libro.get().getStock() > 0) {
            libroToSend.get().setStock(libro.get().getStock());
        }
        if (libro.get().getPrecio() > 0) {
            libroToSend.get().setPrecio(libro.get().getPrecio());
        }
        if (libro.get().getIdAutor() != null) {
            libroToSend.get().setIdAutor(libro.get().getIdAutor());
        }
        if (libro.get().getImageurl() != null) {
            libroToSend.get().setImageurl(libro.get().getImageurl());
        }

        return update(libroToSend);
    }

    @Override
    public Optional<Libro> update(Optional<Libro> libro) {
        String sqlquery = "update Libro set nombre = ?, year = ?, stock = ?, precio = ?, Autor_id = ? , imageurl = ?  where id= ?";
        jdbcTemplate.update(sqlquery,
                libro.get().getNombre(),
                libro.get().getYear(),
                libro.get().getStock(),
                libro.get().getPrecio(),
                libro.get().getIdAutor(),
                libro.get().getImageurl(),
                libro.get().getId()
        );
        return libro;
    }


}