package com.gvendas.gestaovendas.repository;

import com.gvendas.gestaovendas.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaCodigo(Long codigoCategoria);

    @Query("SELECT prod"
            + " from Produto prod"
            + " where prod.codigo = :codigo"
            + " and prod.categoria.codigo = :codigoCategoria")
    Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria);


    Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria,String descricao);
}
