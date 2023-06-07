package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listAll(Long codigoCategoria){
        return produtoRepository.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> finById(Long id){
        return produtoRepository.findById(id);
    }

    public Optional<Produto> buscarPorCodigo(Long id, Long idCategoria){
        return produtoRepository.buscarPorCodigo(id,idCategoria);
    }
}
