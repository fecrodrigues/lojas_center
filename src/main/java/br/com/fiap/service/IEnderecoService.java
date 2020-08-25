package br.com.fiap.service;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Endereco;

public interface IEnderecoService {

    Endereco salvarClienteEndereco(Cliente cliente, Endereco endereco);

}
