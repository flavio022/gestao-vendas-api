package com.gvendas.gestaovendas.services;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.gvendas.gestaovendas.entities.Cliente;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 * The ClienteServico.
 *
 * @author flaviodanilo
 * @Date 11/08/23.
 */

@Service
public class ClienteServico {
    private ClienteRepository clienteRepository;
    public ClienteServico(ClienteRepository clienteRepository){
      this.clienteRepository = clienteRepository;
    }
    public List<Cliente> listAll() {
      return clienteRepository.findAll();
    }
    public Optional<Cliente> listById(Long id) {
      return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente){
      validarClientDuplicado(cliente);
      return clienteRepository.save(cliente);
    }
    public Cliente atualizar(long codigo, Cliente cliente){

      Cliente clienteAtualizar = validarClienteExite(codigo);
      validarClientDuplicado(cliente);
      BeanUtils.copyProperties(cliente,clienteAtualizar,"codigo");

      return clienteRepository.save(clienteAtualizar);
    }
    public void deletar(Long codigo){
      clienteRepository.deleteById(codigo);
    }
    private Cliente validarClienteExite(long codigo){
     Optional<Cliente> cliente = listById(codigo);
     if(cliente.isEmpty()){
       throw new EmptyResultDataAccessException(1);
     }
     return cliente.get();
    }
    public void validarClientDuplicado(Cliente cliente){
      Cliente clientByNome = clienteRepository.findByNome(cliente.getNome());
      if(clientByNome!=null && clientByNome.getCodigo() !=  cliente.getCodigo()){
        throw new RegraNegocioException(String.format("O cliente %s já está cadastrado",
              cliente.getNome().toUpperCase()
        ));
      }
    }
}
