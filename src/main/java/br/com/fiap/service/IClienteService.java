package br.com.fiap.service;

import br.com.fiap.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listarClientes();
    Cliente consultarCliente(Long codigo);
    Cliente adicionarCliente(Cliente cliente);
    Cliente atualizarCliente(Cliente cliente);
    void excluirCliente(Long codigo);

}
