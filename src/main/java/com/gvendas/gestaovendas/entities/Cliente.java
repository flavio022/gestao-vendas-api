package com.gvendas.gestaovendas.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Cliente.
 *
 * @author flaviodanilo
 * @Date 10/08/23.
 */

@Entity
@Table(name = "cliente")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo")
  private Long codigo;
  @Column(name = "nome")
  private String nome;
  @Column(name = "telefone")
  private String telefone;
  @Column(name = "ativo")
  private boolean ativo;

  @Embedded
  private Endereco endereco;

  public Cliente(String nome, String telefone, boolean ativo, Endereco endereco) {
    this.nome = nome;
    this.telefone = telefone;
    this.ativo = ativo;
    this.endereco = endereco;
  }

  public Cliente() {
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
  public boolean getAtivo(){
    return this.ativo;
  }
  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Cliente)) {
      return false;
    }
    Cliente cliente = (Cliente) o;
    return ativo == cliente.ativo && Objects.equals(codigo, cliente.codigo) && Objects.equals(nome,
          cliente.nome) && Objects.equals(telefone, cliente.telefone) && Objects.equals(endereco,
          cliente.endereco);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nome, telefone, ativo, endereco);
  }
}
