package br.com.fiap.model;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoForm {

	private long idCliente;
	private List<Long> produtos;
	private LocalDateTime dataCompra;
	
	public long getIdCliente() {
		return idCliente;
	}
	public List<Long> getProdutos() {
		return produtos;
	}
	public LocalDateTime getDataCompra() {
		return dataCompra;
	}
	
}
