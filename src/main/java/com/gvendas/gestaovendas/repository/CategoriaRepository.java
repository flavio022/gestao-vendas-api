package com.gvendas.gestaovendas.repository;

import com.gvendas.gestaovendas.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Categoria findByNome(String nome);
}
