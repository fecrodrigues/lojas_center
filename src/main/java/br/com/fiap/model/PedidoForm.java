package br.com.fiap.model;

import java.time.LocalDateTime;

import br.com.fiap.entity.Cliente;

public class PedidoForm {

	private Cliente cliente;
	private LocalDateTime dataCompra;
	
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDateTime getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}
	
}
