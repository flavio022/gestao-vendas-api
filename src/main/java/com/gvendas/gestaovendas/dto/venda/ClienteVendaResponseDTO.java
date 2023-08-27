package com.gvendas.gestaovendas.dto.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * The ClienteVendaResponseDTO.
 *
 * @author flaviodanilo
 * @Date 23/08/23.
 */
@ApiModel("Cliente venda retorno DTO")
public class ClienteVendaResponseDTO {

  @ApiModelProperty(value = "Nome cliente")
  private String nome;

  @ApiModelProperty(value = "Venda")
  private List<VendaResponseDTO> vendaResponseDTOs;

  public ClienteVendaResponseDTO(String nome, List<VendaResponseDTO> vendaResponseDTOs) {
    this.nome = nome;
    this.vendaResponseDTOs = vendaResponseDTOs;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<VendaResponseDTO> getVendaResponseDTOs() {
    return vendaResponseDTOs;
  }

  public void setVendaResponseDTOs(List<VendaResponseDTO> vendaResponseDTOs) {
    this.vendaResponseDTOs = vendaResponseDTOs;
  }

}
