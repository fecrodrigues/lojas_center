package br.com.fiap.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable{
	private static final long serialVersionUID = 7950766584876194152L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "codigo_cliente")
	private Cliente cliente;
	
	@ManyToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinTable(name= "pedido_produto", joinColumns= 
	{@JoinColumn(name= "codigo_pedido", nullable= true, updatable= false)},
	inverseJoinColumns= {@JoinColumn(name= "codigo_produto", nullable= true, updatable= false)})
	private List<Produto> produtos;
	
	private LocalDateTime dataCompra;
	
	public Pedido() {}
	
	public Pedido(Cliente cliente, List<Produto> produtos, LocalDateTime dataCompra) {
		this.codigo = 12345;
		this.cliente = cliente;
		this.produtos = produtos;
		this.dataCompra = dataCompra;
	}
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public LocalDateTime getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}

}
