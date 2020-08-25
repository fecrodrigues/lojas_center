package br.com.fiap.service;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Endereco;
import br.com.fiap.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IEnderecoService enderecoService;

    @Override
    public List<Cliente> listarClientes() {
        System.out.println("Listando clientes");
        List<Cliente> clientes = new ArrayList<Cliente>();
        clienteRepository.findAll().forEach(e -> clientes.add(e));
        return clientes;
    }

    @Override
    public Cliente consultarCliente(Long codigo) {
        System.out.println("Consultando cliente " + codigo);
        try {
            return clienteRepository.findById(codigo).get();
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException("Cliente não encontrado");
        }
    }

    @Override
    public Cliente adicionarCliente(Cliente cliente) {
        Endereco endereco = enderecoService.salvarClienteEndereco(cliente, cliente.endereco);
        Cliente clienteAdd = endereco.cliente;
        //Cliente clienteAdd = clienteRepository.save(cliente);

        return clienteAdd;
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        System.out.println("Atualizando cliente " + cliente.codigo );
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.codigo);
        try {
            clienteEncontrado.get();
            enderecoService.salvarClienteEndereco(cliente, cliente.endereco);
            return clienteRepository.save(cliente);
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException("Cliente não encontrado para atualização");
        }
    }

    @Override
    public void excluirCliente(Long codigo) {
        System.out.println("Excluindo cliente " + codigo);
        try {
            clienteRepository.delete(clienteRepository.findById(codigo).get());
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException("Cliente não encontrado");
        }
    }

}
