package br.com.fiap.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class PedidoForm {

	@NotNull(message = "Codigo do cliente é obrigatório")
	@ApiModelProperty(example = "1")
	private Long idCliente;
	
	@NotEmpty(message = "Inclusao do produto é obrigatória")
	private Set<ProdutoForm> produtos;
	
	@NotNull(message = "Data de compra é obrigatório")
	private LocalDateTime dataCompra;
	
	public long getIdCliente() {
		return idCliente;
	}
	public Set<ProdutoForm> getProdutos() {
		return produtos;
	}
	public LocalDateTime getDataCompra() {
		return dataCompra;
	}
	
}
