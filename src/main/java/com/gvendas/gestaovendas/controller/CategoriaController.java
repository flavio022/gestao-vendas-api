package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.services.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
@Api(tags = "Categoria")
public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;
    @ApiOperation(value = "Listar",nickname = "listarTodas")
    @GetMapping
    public List<Categoria>listarTodas(){
        return categoriaService.listAll();
    }
    @ApiOperation(value = "Listar por código")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>>listById(@PathVariable Long id){
        Optional<Categoria> categoria =  categoriaService.listById(id);
        return categoria.isPresent() ? ResponseEntity.ok(categoria): ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salva",nickname = "salvarCategoria")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @ApiOperation(value = "Atualizar",nickname = "atualizarCategoria")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Long id, @RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.atualizar(id,categoria));
    }
    @ApiOperation(value = "Deletar",nickname = "deletarCategoria")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@Valid @PathVariable Long id){
        categoriaService.delete(id);
    }
}

