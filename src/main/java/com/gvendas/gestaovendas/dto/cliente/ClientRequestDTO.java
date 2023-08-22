package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.entities.Cliente;
import com.gvendas.gestaovendas.entities.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * The ClientRequestDTO.
 *
 * @author flaviodanilo
 * @Date 15/08/23.
 */

@ApiModel("cliente requisicao DTO")
public class ClientRequestDTO {
  @ApiModelProperty(value = "Nome")
  @NotBlank(message = "Nome")
  @Length(min = 3,max = 50, message = "Nome")
  private String nome;
  @ApiModelProperty(value = "Telefone")
  @NotBlank(message = "Telefone")
  private String telefone;
  @ApiModelProperty(value = "Ativo")
  @NotNull(message = "Ativo")
  private boolean ativo;
  @ApiModelProperty(value = "Endereco")
  @NotNull(message = "Ativo")
  @Valid
  private EnderecoRequestDTO enderecoRequestDTO;
  public Cliente converterParaEntidade(){

    Endereco endereco =  new Endereco(

          enderecoRequestDTO.getLogradouro(),
          enderecoRequestDTO.getNumero(),
          enderecoRequestDTO.getComplemento(),
          enderecoRequestDTO.getBairro(),
          enderecoRequestDTO.getCep(),
          enderecoRequestDTO.getCidade(),
          enderecoRequestDTO.getEstado());

    return new Cliente(nome,telefone,ativo,endereco);

  }

  public Cliente converterParaEntidade(long codigo){

    Endereco endereco =  new Endereco(

          enderecoRequestDTO.getLogradouro(),
          enderecoRequestDTO.getNumero(),
          enderecoRequestDTO.getComplemento(),
          enderecoRequestDTO.getBairro(),
          enderecoRequestDTO.getCep(),
          enderecoRequestDTO.getCidade(),
          enderecoRequestDTO.getEstado());

    return new Cliente(codigo,nome,telefone,ativo,endereco);

  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public EnderecoRequestDTO getEnderecoRequestDTO() {
    return enderecoRequestDTO;
  }

  public void setEnderecoRequestDTO(EnderecoRequestDTO enderecoRequestDTO) {
    this.enderecoRequestDTO = enderecoRequestDTO;
  }
}
