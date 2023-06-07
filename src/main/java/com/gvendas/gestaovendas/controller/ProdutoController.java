package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ProdutoController {
    public ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }
    @ApiOperation(value = "Listar",nickname = "Listar")
    @GetMapping
    public List<Produto> listarTodas(@PathVariable Long codigoCategoria){
        return produtoService.listAll(codigoCategoria);
    }
    @ApiOperation(value = "Listar por código",nickname = "Listar por código")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Produto>>buscarPorId(
            @PathVariable Long codigoCategoria, @PathVariable Long codigo){
         Optional<Produto> produto = produtoService.buscarPorCodigo(codigo,codigoCategoria);
         return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    
}
