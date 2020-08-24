package br.com.fiap.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable{
	private static final long serialVersionUID = 7950766584876194152L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPedido;
	private Cliente cliente;
	private List<Produto> produtos;
	private LocalDateTime dataCompra;
	
	public Pedido() {}
	
	public Pedido(long idPedido, long idCliente, LocalDateTime dataCompra) {
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.dataCompra = dataCompra;
	}

	
	
	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

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
