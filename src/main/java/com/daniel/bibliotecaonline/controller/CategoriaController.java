package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.ICategoriaRepository;
import com.daniel.bibliotecaonline.dto.Autor;
import com.daniel.bibliotecaonline.dto.Categoria;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "categoria" , produces = "application/json")
@CrossOrigin(origins = "*" )
public class CategoriaController {

    private ICategoriaRepository categoriaRepository;

    public CategoriaController(ICategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("list")
    public Iterable<Categoria> listAll(){
        if (categoriaRepository.findAll().toString() != null) {
            return categoriaRepository.findAll();
        }
        return null;
    }

    @GetMapping("/{categoriaId}")
    public Optional<Categoria> findById(@PathVariable("categoriaId") String id){
        if (categoriaRepository.findById(id).isPresent()) return categoriaRepository.findById(id);
        else return null;
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Categoria> saveCategoria(@RequestBody Optional<Categoria> categoria) {
        return categoriaRepository.save(categoria);
    }

    @PatchMapping(path = "/update/{categoriaId}", consumes = "application/json")
    public Optional<Categoria> updateCategoria(@PathVariable("categoriaId") String categoriaId, @RequestBody Optional<Categoria> categoria) {
        return categoriaRepository.updateCategoria(categoriaId, categoria);
    }

    @DeleteMapping("/delete/{categoriaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoria(@PathVariable("categoriaId") String categoriaId) {
        try {
            categoriaRepository.delete(categoriaId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontro autor");
        }
    }

    @GetMapping("/buscar/{param}")
    public Iterable<Categoria> buscarCategorias(@PathVariable("param") String param){
        return categoriaRepository.buscarCategoria(param);
    }


}
