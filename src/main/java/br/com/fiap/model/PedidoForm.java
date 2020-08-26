package br.com.fiap.model;

import java.time.LocalDateTime;
import java.util.Set;

public class PedidoForm {

	private long idCliente;
	private Set<ProdutoForm> produtos;
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
