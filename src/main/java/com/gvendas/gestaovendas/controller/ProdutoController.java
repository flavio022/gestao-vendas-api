package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import com.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<ProdutoResponseDTO> listarTodas(@PathVariable Long codigoCategoria){
        return produtoService.listAll(codigoCategoria)
              .stream().map(produto -> ProdutoResponseDTO.converteParaProdutoResponseDto(produto))
              .collect(Collectors.toList());
    }
    @ApiOperation(value = "Listar por código",nickname = "Listar por código")
    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO>buscarPorId(
            @PathVariable Long codigoCategoria, @PathVariable Long codigo){
         Optional<Produto> produto = produtoService.buscarPorCodigo(codigo,codigoCategoria);
         return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.converteParaProdutoResponseDto(produto.get())) :
               ResponseEntity.notFound().build();
    }
    @ApiOperation(value = "Salvar produto", nickname = "SalvarProduto" )
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(
          @PathVariable Long codigoCategoria,
          @Valid @RequestBody ProdutoRequestDTO produto ){

        Produto produtoSalvo = produtoService.salvar(codigoCategoria,produto.converterParaEntidade(codigoCategoria));

        return ResponseEntity.status(HttpStatus.CREATED).
              body(ProdutoResponseDTO.converteParaProdutoResponseDto(produtoSalvo));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizarProduto" )
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
          @PathVariable Long codigoCategoria,
          @PathVariable Long codigoProduto,
          @Valid @RequestBody ProdutoRequestDTO produto){
        Produto produtoAtualizado = produtoService.atualizar(codigoCategoria,codigoProduto,
              produto.converterParaEntidade(codigoCategoria,
              codigoProduto));
        return ResponseEntity.ok(ProdutoResponseDTO.converteParaProdutoResponseDto(produtoAtualizado));
    }

    @ApiOperation(value = "Deletar", nickname = "deletarProduto" )
    @DeleteMapping("/{codigoProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto){
        produtoService.deletar(codigoCategoria,codigoProduto);
    }
}
