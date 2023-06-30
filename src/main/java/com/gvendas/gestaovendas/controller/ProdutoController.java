package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "Salvar produto", nickname = "SalvarProduto" )
    @PostMapping
    public ResponseEntity<Produto> salvar(
          @PathVariable Long codigoCategoria,
          @Valid @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(codigoCategoria,produto));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizarProduto" )
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<Produto> atualizar(
          @PathVariable Long codigoCategoria,
          @PathVariable Long codigoProduto,
          @Valid @RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.atualizar(codigoCategoria,codigoProduto,produto));
    }

    @ApiOperation(value = "Deletar", nickname = "deletarProduto" )
    @DeleteMapping("/{codigoProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto){
        produtoService.deletar(codigoCategoria,codigoProduto);
    }
}
