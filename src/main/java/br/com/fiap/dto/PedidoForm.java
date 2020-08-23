package br.com.fiap.dto;

import java.time.LocalDateTime;

public class PedidoForm {

	private long idCliente;
	private LocalDateTime dataCompra;
	
	
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public LocalDateTime getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}
	
}
