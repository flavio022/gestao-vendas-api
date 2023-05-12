package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria>listarTodas(){
        return categoriaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>>listById(@PathVariable Long id){
        Optional<Categoria> categoria =  categoriaService.listById(id);
        return categoria.isPresent() ? ResponseEntity.ok(categoria): ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Long id, @RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.atualizar(id,categoria));
    }
}
