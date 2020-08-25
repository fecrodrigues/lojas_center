package br.com.fiap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(example = "1", hidden = true)
    private Long codigo;

    @NotNull(message = "Nome é obrigatório")
    @Size(max = 170, message = "Nome deve conter no máximo 170 caracteres")
    @ApiModelProperty(example = "José da Silva")
    private String nome;

    @NotNull(message = "Data de Nascimento é obrigatória")
    @ApiModelProperty(example = "1980-08-23")
    private Date data_nascimento;

    @NotNull(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF inválido")
    @ApiModelProperty(example = "45816821451")
    private String cpf;

    @NotNull(message = "Nome da mãe é obrigatório")
    @Size(max = 170, message = "Nome da mãe deve conter no máximo 170 caracteres")
    @ApiModelProperty(example = "Maria de Silva")
    private String nome_mae;

    @NotNull(message = "Email é obrigatório")
    @Email(message = "Email é inválido")
    @Size(max = 200, message = "Email deve conter no máximo 200 caracteres")
    @ApiModelProperty(example = "jose.silva@gmail.com")
    private String email;

    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY, mappedBy= "cliente")
    private List<Pedido> pedidos;

    @NotNull(message = "Endereco é obrigatório")
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Endereco endereco;

    public Cliente(Long codigo, String nome, Date data_nascimento, String cpf, String nome_mae, String email, List<Pedido> pedidos, Endereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.nome_mae = nome_mae;
        this.email = email;
        this.pedidos = pedidos;
        this.endereco = endereco;
    }

    public Cliente() { super(); };

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

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome_mae() {
        return nome_mae;
    }

    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
