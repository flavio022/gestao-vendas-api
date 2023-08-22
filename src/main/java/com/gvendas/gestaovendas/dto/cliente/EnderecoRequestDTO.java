package com.gvendas.gestaovendas.dto.cliente;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * The EnderecoRequestDTO.
 *
 * @author flaviodanilo
 * @Date 16/08/23.
 */

@ApiModel("Requisição DTO")
public class EnderecoRequestDTO {
  @ApiModelProperty(value = "Logradouro")
  @NotBlank(message = "Logradouro")
  @Length(min = 3,max = 30, message = "Logradouro")
  private String logradouro;
  @ApiModelProperty(value = "Numero")
  @NotNull(message = "Numero")
  private Integer numero;
  @ApiModelProperty(value = "Complemento")
  @NotNull(message = "Complemento")
  private String complemento;
  @ApiModelProperty(value = "Bairro")
  @NotBlank(message = "Bairro")
  private String bairro;
  @ApiModelProperty(value = "Cep")
  @NotBlank(message = "Telefone")
  private String cep;
  @ApiModelProperty(value = "Cidade")
  @NotBlank(message = "Cidade")
  private String cidade;
  @ApiModelProperty(value = "Estado")
  @NotBlank(message = "Estado")
  private String estado;

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

}
