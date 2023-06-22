package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.ILibroCategoriaRepository;
import com.daniel.bibliotecaonline.dao.impl.LibroRepository;
import com.daniel.bibliotecaonline.dto.Libro;
import com.daniel.bibliotecaonline.dto.LibroCategoria;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "librocategoria", produces = "application/json")
@CrossOrigin(origins = "*")
public class LibroCategoriaController {

    Logger logger
            = Logger.getLogger(
            LibroRepository.class.getName());

    private ILibroCategoriaRepository LibroCategoriaRepository;

    public LibroCategoriaController(ILibroCategoriaRepository iLibroCategoriaRepository) {
        this.LibroCategoriaRepository = iLibroCategoriaRepository;
    }

    @GetMapping("list")
    public Iterable<LibroCategoria> listAll() {
        if (LibroCategoriaRepository.findAll().toString() != null) {
            return LibroCategoriaRepository.findAll();
        }
        return null;
    }

    @GetMapping({"/{libroId}"})
    public List<LibroCategoria> findById(@PathVariable("libroId") String id) {
        if (LibroCategoriaRepository.findById(id) != null ) return LibroCategoriaRepository.findById(id);
        else return null;
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public LibroCategoria save(@RequestBody LibroCategoria libro) {
        return LibroCategoriaRepository.save(libro);
    }

    @PatchMapping(path = "/update/{libroId}", consumes = "application/json")
    public void update(@PathVariable("libroId") String libroId, @RequestBody String categorias) {
        LibroCategoriaRepository.update(libroId, categorias);
    }

    @DeleteMapping("/delete/{libroId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLibroCategorias(@PathVariable("libroId") String libroId) {
        try {
            LibroCategoriaRepository.delete(libroId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontro libro");
        }
    }

//    @PatchMapping(path = "/update/{libroId}", consumes = "application/json")
//    public LibroCategoria update(@PathVariable("libroId") String libroId, @RequestBody LibroCategoria libro) {
//        logger.info(libro.getIdLibro());
//        logger.info(libro.getIdCategoria());
//        return LibroCategoriaRepository.updateLibroCategoria(libroId, libro);
//    }
//
//    @DeleteMapping("/delete/{libroId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteLibro(@PathVariable("libroId") String libroId) {
//        try {
//            LibroCategoriaRepository.delete(libroId);
//        } catch (EmptyResultDataAccessException e) {
//            System.out.println("No se encontro libro");
//        }
//    }

}
