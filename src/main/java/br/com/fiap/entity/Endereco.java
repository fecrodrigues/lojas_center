package br.com.fiap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    public long codigo_cliente;

    public String cep;
    public String logradouro;
    public String cidade;
    public String estado;
    public Integer numero;
    public String complemento;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "codigo_cliente")
    @MapsId
    public Cliente cliente;
}
