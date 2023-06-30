package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.entities.Produto;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repository.CategoriaRepository;
import com.gvendas.gestaovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository){

        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;

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
    public Produto salvar(Produto produto){
        validarCategoriaDoProdutoExistente(produto.getCategoria().getCodigo());
        validarProdutoDuplicado(produto);
        return produtoRepository.save(produto);
    }

    private void validarProdutoDuplicado(Produto produto){

        if(produtoRepository.findByCategoriaCodigoAndDescricao(
                produto.getCategoria().getCodigo(),
                produto.getDescricao()).isPresent()){
            throw new RegraNegocioException(String.format(
                    "O Produto %s ja esta cadastrado!",
                    produto.getDescricao()));
        }
    }
    private void validarCategoriaDoProdutoExistente(Long codigoCategoria){

        if(codigoCategoria==null){
            throw new RegraNegocioException("A categoria não pode ser nula");
        }
        if(categoriaRepository.findById(codigoCategoria).isEmpty()){
            throw new RegraNegocioException(
                    String.format("A categoria de código %s informada não existe no cadastro",codigoCategoria));
        }
    }
}
