package br.com.fiap.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ControleEstoqueProduto {

    @NotNull(message = "Código é obrigatório")
    @Positive(message = "Código não pode ser negativo ou zero")
    @ApiModelProperty(example = "1")
    private Long codigo;

    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade não pode ser negativa ou zero")
    @ApiModelProperty(example = "3")
    private Integer quantidade;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
