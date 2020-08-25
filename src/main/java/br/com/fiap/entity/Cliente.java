package br.com.fiap.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(example = "1", hidden = true)
    public Long codigo;

    @NotNull(message = "Nome é obrigatório")
    @Size(max = 170, message = "Nome deve conter no máximo 170 caracteres")
    @ApiModelProperty(example = "José da Silva")
    public String nome;

    @NotNull(message = "Data de Nascimento é obrigatória")
    @ApiModelProperty(example = "1980-08-23")
    public Date data_nascimento;

    @NotNull(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF inválido")
    @ApiModelProperty(example = "45816821451")
    public String cpf;

    @NotNull(message = "Nome da mãe é obrigatório")
    @Size(max = 170, message = "Nome da mãe deve conter no máximo 170 caracteres")
    @ApiModelProperty(example = "Maria de Silva")
    public String nome_mae;

    @NotNull(message = "Email é obrigatório")
    @Email(message = "Email é inválido")
    @Size(max = 200, message = "Email deve conter no máximo 200 caracteres")
    @ApiModelProperty(example = "jose.silva@gmail.com")
    public String email;

    @NotNull(message = "Endereco é obrigatório")
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    public Endereco endereco;

    public Cliente(Long codigo, String nome, Date data_nascimento, String cpf, String nome_mae, String email, Endereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.nome_mae = nome_mae;
        this.email = email;
        this.endereco = endereco;
    }

    public Cliente() {
        super();
    }
}
