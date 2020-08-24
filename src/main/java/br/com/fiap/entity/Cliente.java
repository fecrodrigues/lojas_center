package br.com.fiap.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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
