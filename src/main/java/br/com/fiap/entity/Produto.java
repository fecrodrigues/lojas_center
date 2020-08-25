package br.com.fiap.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(example = "1", hidden = true)
	public Long codigo;

	@NotNull(message = "Nome é obrigatório")
	@Size(max = 255, message = "Nome de usuário deve conter no máximo 255 caracteres")
	@ApiModelProperty(example = "Produto de Teste")
	public String nome;

	@NotNull(message = "Quantidade é obrigatória")
	@Positive(message = "Quantidade não pode ser negativa ou zero")
	@ApiModelProperty(example = "10")
	public Integer quantidade;

	@NotNull(message = "Valor é obrigatório")
	@Positive(message = "Valor não pode ser negativo ou zero")
	@ApiModelProperty(example = "10.50")
	public BigDecimal valor;
	
	public Produto(long id, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Produto() {
		super();
	}
}