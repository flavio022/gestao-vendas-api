package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.entities.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * The ClienteResponseDTO.
 *
 * @author flaviodanilo
 * @Date 11/08/23.
 */
@ApiModel("cliente response DTO")
public class ClienteResponseDTO {
  @ApiModelProperty(value = "Codigo")
  @NotBlank(message = "Codigo")
  private Long codigo;
  @ApiModelProperty(value = "Nome")
  @NotBlank(message = "Nome")
  @Length(min = 3,max=50, message = "Nome")
  private String nome;
  @ApiModelProperty(value = "Telefone")
  private String telefone;
  @ApiModelProperty(value = "Ativo")
  private boolean ativo;

  private EnderecoResponseDTO enderecoResponseDTO;

  public ClienteResponseDTO(Long codigo, String nome, String telefone, boolean ativo,
        EnderecoResponseDTO enderecoResponseDTO) {
    this.codigo = codigo;
    this.nome = nome;
    this.telefone = telefone;
    this.ativo = ativo;
    this.enderecoResponseDTO = enderecoResponseDTO;
  }

  public static ClienteResponseDTO converteParaClienteDTO(Cliente cliente){
    return new ClienteResponseDTO(
          cliente.getCodigo(),
          cliente.getNome(),
          cliente.getTelefone(),
          cliente.getAtivo(),
          new EnderecoResponseDTO(cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getComplemento(),
                cliente.getEndereco().getBairro(),
                cliente.getEndereco().getCep(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getEstado()));
  }

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
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

  public EnderecoResponseDTO getEnderecoResponseDTO() {
    return enderecoResponseDTO;
  }

  public void setEnderecoResponseDTO(EnderecoResponseDTO enderecoResponseDTO) {
    this.enderecoResponseDTO = enderecoResponseDTO;
  }
}
