package com.gvendas.gestaovendas.repository;

import com.gvendas.gestaovendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The ClienteRepository.
 *
 * @author flaviodanilo
 * @Date 11/08/23.
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
  Cliente findByNome(String nome);
}
