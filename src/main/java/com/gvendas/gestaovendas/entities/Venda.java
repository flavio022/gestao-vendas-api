package com.gvendas.gestaovendas.entities;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Venda.
 *
 * @author flaviodanilo
 * @Date 23/08/23.
 */
@Entity
@Table(name = "venda")
public class Venda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo")
  private Long codigo;
  @Column(name = "data")
  private LocalDate data;
  @ManyToOne
  @JoinColumn(name = "codigo_cliente",referencedColumnName = "codigo")
  private Cliente cliente;

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Venda)) {
      return false;
    }
    Venda venda = (Venda) o;
    return Objects.equals(codigo, venda.codigo) && Objects.equals(data, venda.data)
          && Objects.equals(cliente, venda.cliente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, data, cliente);
  }
}
