package com.gvendas.gestaovendas.dto.produto;

import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.gvendas.gestaovendas.entities.Categoria;
import com.gvendas.gestaovendas.entities.Produto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


/**
 * The ProdutoRequestDTO.
 *
 * @author flaviodanilo
 * @Date 10/07/23.
 */

@ApiModel("Produto Requiscao DTO")
public class ProdutoRequestDTO {
  @ApiModelProperty(value = "Descricao")
  @NotBlank(message = "Descricao")
  @Length(min = 3,max=100, message = "Descricao")
  private String descricao;

  @ApiModelProperty(value = "Quantidade")
  private Integer quantidade;

  @ApiModelProperty(value = "Preço Custo")
  private BigDecimal precoCusto;

  @ApiModelProperty(value = "Preço venda")
  private BigDecimal precoVenda;

  @ApiModelProperty(value = "Observação")
  @Length(max = 500, message = "Observação")
  private String observacao;

  public Produto converterParaEntidade(Long codigoCategiria){
    return new Produto(
          descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategiria));
  }
  public Produto converterParaEntidade(Long codigoCategiria,Long codigoProduto){
    return new Produto(
          codigoProduto,
          descricao,
          quantidade,
          precoCusto,
          precoVenda,
          observacao,
          new Categoria(codigoCategiria));
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public BigDecimal getPrecoCusto() {
    return precoCusto;
  }

  public void setPrecoCusto(BigDecimal precoCusto) {
    this.precoCusto = precoCusto;
  }

  public BigDecimal getPrecoVenda() {
    return precoVenda;
  }

  public void setPrecoVenda(BigDecimal precoVenda) {
    this.precoVenda = precoVenda;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }
}
