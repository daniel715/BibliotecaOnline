package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.ILibroRepository;
import com.daniel.bibliotecaonline.dto.Libro;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "libro", produces = "application/json")
@CrossOrigin(origins = "*")
public class LibroController {
    private ILibroRepository libroRepository;

    Logger logger
            = Logger.getLogger(
            LibroController.class.getName());

    public LibroController(ILibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping("list")
    public Iterable<Libro> listAll() {
        if (libroRepository.findAll().toString() != null) {
            return libroRepository.findAll();
        }
        return null;
    }

    @GetMapping({"/{libroId}"})
    public Libro findById(@PathVariable("libroId") String id) {
        if (libroRepository.findById(id) != null ) return libroRepository.findById(id);
        else return null;
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Libro saveLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PatchMapping(path = "/update/{libroId}", consumes = "application/json")
    public Libro updateLibro(@PathVariable("libroId") String libroId, @RequestBody Libro libro) {
        return libroRepository.updateLibro(libroId, libro);
    }

    @DeleteMapping("/delete/{libroId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLibro(@PathVariable("libroId") String libroId) {
        try {
            libroRepository.delete(libroId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontro libro");
        }
    }

    @GetMapping("/buscar/{param}")
    public Iterable<Libro> buscarLibros(@PathVariable("param") String param){
        return libroRepository.buscarLibros(param);
    }

    @GetMapping("/buscarporcategoria/{param}")
    public Iterable<Libro> buscarporcategoria(@PathVariable("param") String param){
        return libroRepository.buscarporcategoria(param);
    }

}
