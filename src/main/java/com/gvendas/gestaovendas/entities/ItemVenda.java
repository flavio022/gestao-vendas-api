package com.gvendas.gestaovendas.entities;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The ItemVenda.
 *
 * @author flaviodanilo
 * @Date 23/08/23.
 */
@Entity
@Table(name = "item_venda")
public class ItemVenda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo")
  private Long codigo;
  @Column(name = "quantidade")
  private Integer quantidade;
  @Column(name = "preco_vendido")
  private BigDecimal precoVendido;
  @ManyToOne
  @JoinColumn(name = "codigo_produto",referencedColumnName = "codigo")
  private Produto produto;

  @ManyToOne
  @JoinColumn(name = "codigo_venda",referencedColumnName = "codigo")
  private Venda venda;


  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public BigDecimal getPrecoVendido() {
    return precoVendido;
  }

  public void setPrecoVendido(BigDecimal precoVendido) {
    this.precoVendido = precoVendido;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public Venda getVenda() {
    return venda;
  }

  public void setVenda(Venda venda) {
    this.venda = venda;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ItemVenda)) {
      return false;
    }
    ItemVenda itemVenda = (ItemVenda) o;
    return Objects.equals(codigo, itemVenda.codigo) && Objects.equals(quantidade, itemVenda.quantidade)
          && Objects.equals(precoVendido, itemVenda.precoVendido) && Objects.equals(produto,
          itemVenda.produto) && Objects.equals(venda, itemVenda.venda);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, quantidade, precoVendido, produto, venda);
  }
}
