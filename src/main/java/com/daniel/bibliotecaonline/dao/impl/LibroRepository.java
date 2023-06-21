package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.ILibroRepository;
import com.daniel.bibliotecaonline.dto.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class LibroRepository implements ILibroRepository {

    Logger logger
            = Logger.getLogger(
            LibroRepository.class.getName());
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LibroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Libro> findAll() {
        return jdbcTemplate.query("call Libro_list()", this::mapRowToLibro);
    }

    @Override
    public Libro findById(String id) {
        List<Libro> results = jdbcTemplate.query("call Libro_get_by_id(?);", this::mapRowToLibro, id);
        return results.size() == 0 ?
                null :
                results.get(0);
    }

    @Override
    public Libro save(Libro libro) {
        jdbcTemplate.update(
                "call Libro_save(?,?,?,?,?,?,?,?)",
                libro.getIdLibro(),
                libro.getNombre(),
                libro.getYear(),
                libro.getPrecio(),
                libro.getIdAutor(),
                libro.getResumen(),
                libro.getSelled(),
                libro.getIdPedido()
        );
        return libro;
    }

    @Override
    public Libro updateLibro(String libroId, Libro libro) {
        Libro libroToSend = this.findById(libroId);
        if (libro.getNombre() != null) {
            libroToSend.setNombre(libro.getNombre());
        }
        if (libro.getYear() != null) {
            libroToSend.setYear(libro.getYear());
        }
        if (libro.getPrecio() > 0) {
            libroToSend.setPrecio(libro.getPrecio());
        }
        if (libro.getIdAutor() != null) {
            libroToSend.setIdAutor(libro.getIdAutor());
        }
        if (libro.getResumen() != null) {
            libroToSend.setResumen(libro.getResumen());
        }
        if (libro.getSelled() != null) {
            libroToSend.setSelled(libro.getSelled());
        }
        if (libro.getIdPedido() != null) {
            libroToSend.setIdPedido(libro.getIdPedido());
        }

        String sqlquery = "call Libro_update(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sqlquery,
                libroToSend.getIdLibro(),
                libroToSend.getNombre(),
                libroToSend.getYear(),
                libroToSend.getPrecio(),
                libroToSend.getIdAutor(),
                libroToSend.getResumen(),
                libroToSend.getSelled(),
                libroToSend.getIdPedido());
        return libroToSend;
    }

    @Override
    public void delete(String libroId) {
        String sqlquery = "call Libro_delete(?)";
        jdbcTemplate.update(sqlquery, libroId);
    }

    @Override
    public Iterable<Libro> buscarLibros(String param) {
        String sqlquery = "SELECT * FROM libro WHERE nombre LIKE '%" + param + "%'  UNION ALL SELECT * FROM libro WHERE id_autor in (select id_autor from autor where nombre LIKE '%" + param + "%')";
        return jdbcTemplate.query(sqlquery, this::mapRowToLibro);
    }

    @Override
    public Iterable<Libro> buscarporcategoria(String param) {
        String sqlquery = "call buscar_libro_by_categoriaId(?)";
        return jdbcTemplate.query(sqlquery, this::mapRowToLibro,param);
    }

    private Libro mapRowToLibro(ResultSet row, int rowNum)
            throws SQLException {
        return new Libro(
                row.getString("id_libro"),
                row.getString("nombre"),
                row.getString("year"),
                row.getFloat("precio"),
                row.getString("id_autor"),
                row.getString("resumen"),
                row.getBoolean("is_selled"),
                row.getString("id_pedido")
        );
    }
}