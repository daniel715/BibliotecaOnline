package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.ILibroCategoriaRepository;
import com.daniel.bibliotecaonline.dto.LibroCategoria;
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

}
