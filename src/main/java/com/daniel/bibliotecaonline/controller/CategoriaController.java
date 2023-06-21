package com.daniel.bibliotecaonline.controller;

import com.daniel.bibliotecaonline.dao.ICategoriaRepository;
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

    @GetMapping("/{categoriaId}")
    public Categoria findById(@PathVariable("categoriaId") String id){
        if (categoriaRepository.findById(id) != null ) return categoriaRepository.findById(id);
        else return null;
    }

    @GetMapping("list")
    public Iterable<Categoria> listAll(){
        if (categoriaRepository.findAll().toString() != null) {
            return categoriaRepository.findAll();
        }
        return null;
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria saveCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PatchMapping(path = "/update/{categoriaId}", consumes = "application/json")
    public Categoria updateCategoria(@PathVariable("categoriaId") String categoriaId, @RequestBody Categoria categoria) {
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
