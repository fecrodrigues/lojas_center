package br.com.fiap.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long codigo;

    public String nome;
    public Date data_nascimento;
    public String cpf;
    public String nome_mae;
    public String email;
    
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "codigo_pedido")
    private List<Pedido> pedidos;

    public Cliente(Long codigo, String nome, Date data_nascimento, String cpf, String nome_mae, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.nome_mae = nome_mae;
        this.email = email;
    }

    public Cliente() {
        super();
    }
}
