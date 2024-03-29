package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.gvendas.gestaovendas.dto.cliente.ClientRequestDTO;
import com.gvendas.gestaovendas.entities.Cliente;
import com.gvendas.gestaovendas.services.ClienteServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
/**
 * The ClienteController.
 *
 * @author flaviodanilo
 * @Date 11/08/23.
 */

@Api(tags = "Cliente")
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
  private ClienteServico clienteServico;
  public ClienteController(ClienteServico clienteServico){
    this.clienteServico = clienteServico;
  }
  @ApiOperation(value = "Listar",nickname = "listarTodosClientes")
  @GetMapping
  public List<ClienteResponseDTO> listarTodos(){

    return clienteServico.listAll().stream().map(
          cliente -> ClienteResponseDTO.converteParaClienteDTO(cliente))
          .collect(Collectors.toList());
  }
  @ApiOperation(value = "Deletar",nickname = "deletarCliente")
  @DeleteMapping(value = "/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long codigo){
    clienteServico.deletar(codigo);
  }
  @ApiOperation(value = "Listar por código",nickname="buscarClientesPorId")
  @GetMapping("/{id}")
  public ResponseEntity<ClienteResponseDTO> listById(@PathVariable Long id){
    Optional<Cliente> cliente =  clienteServico.listById(id);
    return cliente.isPresent() ? ResponseEntity.ok(
          ClienteResponseDTO.converteParaClienteDTO(cliente.get()))
          : ResponseEntity.notFound().build();
  }
  @ApiOperation(value = "Atualizar cliente",nickname = "atualizar")
  @PutMapping("/{codigo}")
  public ResponseEntity<ClienteResponseDTO> atualizar(
        @PathVariable long codigo, @Valid
        @RequestBody ClientRequestDTO clienteDto){

    Cliente clienteAtualizado = clienteServico.atualizar(codigo, clienteDto.converterParaEntidade(codigo));

    return ResponseEntity.ok(ClienteResponseDTO.converteParaClienteDTO(clienteAtualizado));
  }
  @ApiOperation(value = "Salvar",nickname="salvarCliente")
  @PostMapping
  public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClientRequestDTO clientRequestDTO){

    Cliente clienteSalvo = clienteServico.salvar(clientRequestDTO.converterParaEntidade());
    return ResponseEntity.status(HttpStatus.CREATED)
          .body(ClienteResponseDTO.converteParaClienteDTO(clienteSalvo));
  }

}
