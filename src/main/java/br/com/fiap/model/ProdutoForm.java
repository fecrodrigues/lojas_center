package br.com.fiap.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;

public class ProdutoForm {

	@NotNull(message = "Codigo do produto é obrigatório")
	@ApiModelProperty(example = "1")
	private Long codigo;
	
	@NotNull(message = "Quantidade é obrigatória")
	@PositiveOrZero(message = "Quantidade não pode ser negativa")
	@ApiModelProperty(example = "10")
	private Integer quantidade;
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
