package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> listById(Long id){
        return categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria){
        validarCategoriaDuplicada(categoria);
        return categoriaRepository.save(categoria);
    }
    public Categoria atualizar(Long id, Categoria categoria){
        Categoria categoriaSalvar = validarSeCategoriaExiste(id);
        validarCategoriaDuplicada(categoria);
        BeanUtils.copyProperties(categoria,categoriaSalvar,"codigo");
        return categoriaRepository.save(categoriaSalvar);
    }

    private Categoria validarSeCategoriaExiste(Long id) {
        Optional<Categoria> categoria = listById(id);
        if(categoria.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return categoria.get();
    }
    public void delete(Long id){
        categoriaRepository.deleteById(id);
    }
    public void validarCategoriaDuplicada(Categoria categoria){
        Categoria categoriaEncontrada = categoriaRepository.findByNome(categoria.getNome());
        if(categoriaEncontrada != null
                && categoriaEncontrada.getCodigo() != categoria.getCodigo()){
            throw new RegraNegocioException(
                    String.format("A categoria %s já esta cadastrada",
                    categoria.getNome()).toUpperCase());
        }
    }
}
