package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.impl.LibroRepository;
import com.daniel.bibliotecaonline.dto.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping(path = "libro", produces = "application/json")
@CrossOrigin(origins = "*")
public class LibroController {
    private LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
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
    public Optional<Libro> findById(@PathVariable("libroId") String id) {
        if (libroRepository.findById(id).isPresent()) return libroRepository.findById(id);
        else return null;
    }

    @PostMapping(path = "/save",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Libro> saveLibro( @RequestBody Optional<Libro> libro){
            return libroRepository.save(libro);
    }

    @PatchMapping(path = "/update/{libroId}", consumes = "application/json")
    public Optional<Libro> updateLibro(@PathVariable("libroId") String libroId , @RequestBody Optional<Libro> libro ){
            return libroRepository.updateLibro(libroId, libro);
    }

}
