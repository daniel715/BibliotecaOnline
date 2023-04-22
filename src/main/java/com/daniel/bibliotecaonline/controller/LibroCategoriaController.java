package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.ILibroCategoriaRepository;
import com.daniel.bibliotecaonline.dto.Libro;
import com.daniel.bibliotecaonline.dto.LibroCategoria;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "librocategoria", produces = "application/json")
@CrossOrigin(origins = "*")
public class LibroCategoriaController {

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
