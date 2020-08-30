package br.com.fiap.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(example = "1", hidden = true)
	private Long codigo;

	@NotNull(message = "Nome é obrigatório")
	@Size(max = 255, message = "Nome de usuário deve conter no máximo 255 caracteres")
	@ApiModelProperty(example = "Produto de Teste")
	private String nome;

	@NotNull(message = "Quantidade é obrigatória")
	@PositiveOrZero(message = "Quantidade não pode ser negativa")
	@ApiModelProperty(example = "10")
	private Integer quantidade;

	@NotNull(message = "Valor é obrigatório")
	@Positive(message = "Valor não pode ser negativo ou zero")
	@ApiModelProperty(example = "10.50")
	private BigDecimal valor;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "produtos")
	private List<Pedido> pedidos;
	
	public Produto(long id, String nome) {
		super();
		this.nome = nome;
	}

	public Produto() {
		super();
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}