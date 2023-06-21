package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.IAutorRepository;
import com.daniel.bibliotecaonline.dto.Autor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "autor", produces = "application/json")
@CrossOrigin(origins = "*")
public class AutorController {

    private final IAutorRepository iAutorRepository;

    public AutorController(IAutorRepository autorRepository) {
        this.iAutorRepository = autorRepository;
    }

    @GetMapping("list")
    public Object listAll() {
        if (iAutorRepository.findAll().toString() != null) {
            return iAutorRepository.findAll();
        }
        return null;
    }

    @GetMapping("/find/{autorId}")
    public Autor findById(@PathVariable("autorId") String id) {
        if (iAutorRepository.findById(id) != null) return iAutorRepository.findById(id);
        else return null;
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Autor saveAutor(@RequestBody Autor autor) {
        return iAutorRepository.save(autor);
    }

    @PatchMapping(path = "/update/{autorId}", consumes = "application/json")
    public Autor updateAutor(@PathVariable("autorId") String autorId, @RequestBody Autor autor) {
        return iAutorRepository.updateAutor(autorId, autor);
    }

    @DeleteMapping("/delete/{autorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAutor(@PathVariable("autorId") String autorId) {
        try {
            iAutorRepository.delete(autorId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontro autor");
        }
    }

    @GetMapping("/buscar/{param}")
    public Iterable<Autor> buscarAutores(@PathVariable("param") String param) {
        return iAutorRepository.buscarAutor(param);
    }

}
