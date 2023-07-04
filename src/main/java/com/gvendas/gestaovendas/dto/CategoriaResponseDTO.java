package com.gvendas.gestaovendas.dto;

import com.gvendas.gestaovendas.entities.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * The CategoriaResponseDTO.
 *
 * @author flaviodanilo
 * @Date 04/07/23.
 */

@ApiModel("categoria retorno DTO")
public class CategoriaResponseDTO {
  @ApiModelProperty(value = "Codigo")
  private Long codigo;
  @ApiModelProperty(value = "Nome")
  private String nome;

  public CategoriaResponseDTO(Long codigo, String nome) {
    this.codigo = codigo;
    this.nome = nome;
  }

  public static CategoriaResponseDTO converteParaCategoriaDTo(Categoria categoria) {
    return new CategoriaResponseDTO(categoria.getCodigo(),categoria.getNome());
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
}

