package br.com.fiap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    public long codigo_cliente;

    @NotNull(message = "CEP é obrigatório")
    @Size(min = 8, max = 8, message = "CEP inválido")
    @ApiModelProperty(example = "03333000")
    public String cep;

    @NotNull(message = "Logradouro é obrigatório")
    @Size(max = 170, message = "Logradouro deve conter no máximo 170 caracteres")
    @ApiModelProperty(example = "Rua Marechal Barbacena")
    public String logradouro;

    @NotNull(message = "Cidade é obrigatória")
    @Size(max = 150, message = "Cidade deve conter no máximo 150 caracteres")
    @ApiModelProperty(example = "São Paulo")
    public String cidade;

    @NotNull(message = "Estado é obrigatório")
    @Size(max = 150, message = "Estado deve conter no máximo 150 caracteres")
    @ApiModelProperty(example = "SP")
    public String estado;

    @NotNull(message = "Número é obrigatório")
    @Positive(message = "Número não pode ser negativo ou zero")
    @ApiModelProperty(example = "12")
    public Integer numero;

    @Size(max = 255, message = "Complemento deve conter no máximo 255 caracteres")
    @ApiModelProperty(example = "Apto 10")
    public String complemento;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "codigo_cliente")
    @MapsId
    public Cliente cliente;
}
