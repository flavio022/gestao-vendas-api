package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.dto.categoria.CategoriaRequestDTO;
import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.services.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.stream.Collectors;
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
    public List<CategoriaResponseDTO>listarTodas(){
        return categoriaService.listAll().stream()
              .map(categoria -> CategoriaResponseDTO.converteParaCategoriaDTo(categoria))
              .collect(Collectors.toList());
    }
    @ApiOperation(value = "Listar por código")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO>listById(@PathVariable Long id){
        Optional<Categoria> categoria =  categoriaService.listById(id);
        return categoria.isPresent() ? ResponseEntity.ok(
              CategoriaResponseDTO.converteParaCategoriaDTo(categoria.get()))
              : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salva",nickname = "salvarCategoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaDto){
        Categoria categoriaSalva = categoriaService.salvar(categoriaDto.converterParaEntindade());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converteParaCategoriaDTo(categoriaSalva));
    }

    @ApiOperation(value = "Atualizar",nickname = "atualizarCategoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@Valid @PathVariable Long id, @RequestBody Categoria categoria){
        Categoria categoriaAtualizada = categoriaService.atualizar(id,categoria);
        return ResponseEntity.ok(CategoriaResponseDTO.converteParaCategoriaDTo(categoriaAtualizada));
    }
    @ApiOperation(value = "Deletar",nickname = "deletarCategoria")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@Valid @PathVariable Long id){
        categoriaService.delete(id);
    }
}

