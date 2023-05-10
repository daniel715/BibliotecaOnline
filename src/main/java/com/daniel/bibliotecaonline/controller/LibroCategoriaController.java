package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.ILibroCategoriaRepository;
import com.daniel.bibliotecaonline.dao.impl.LibroRepository;
import com.daniel.bibliotecaonline.dto.Libro;
import com.daniel.bibliotecaonline.dto.LibroCategoria;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<LibroCategoria> saveLibroCategoria(@RequestBody Optional<LibroCategoria> libro) {
        return LibroCategoriaRepository.save(libro);
    }

    @GetMapping({"/{libroId}"})
    public Optional<LibroCategoria> findCategoriasByLibroId(@PathVariable("libroId") String id) {
            return LibroCategoriaRepository.findCategoriasByLibroId(id);
    }

    @PatchMapping(path = "/update/{libroId}", consumes = "application/json")
    public Optional<LibroCategoria> updateLibroCategoria(@PathVariable("libroId") String libroId, @RequestBody Optional<LibroCategoria> libro) {
        logger.info(libro.get().getLibroId());
        logger.info(libro.get().getCategorias());
        logger.info(libro.get().getCategoriasId());
        return LibroCategoriaRepository.updateLibroCategoria(libroId, libro);
    }

    @DeleteMapping("/delete/{libroId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLibro(@PathVariable("libroId") String libroId) {
        try {
            LibroCategoriaRepository.delete(libroId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontro libro");
        }
    }

}
