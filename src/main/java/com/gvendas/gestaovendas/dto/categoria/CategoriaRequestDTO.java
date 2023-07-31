package com.gvendas.gestaovendas.dto.categoria;

import com.gvendas.gestaovendas.entities.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * The CategoriaRequestDTO.
 *
 * @author flaviodanilo
 * @Date 05/07/23.
 */
@ApiModel("categoria requisicao DTO")
public class CategoriaRequestDTO {

  @ApiModelProperty(value = "Nome")
  @NotBlank(message = "Nome")
  @Length(min = 3,max=50, message = "Nome")
  private String nome;

  public Categoria converterParaEntindade(){
    return new Categoria(nome);
  }

  public Categoria converterParaEntindade(Long codigo){
    return new Categoria(codigo,nome);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
