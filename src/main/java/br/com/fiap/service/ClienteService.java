package br.com.fiap.service;

import br.com.fiap.entity.Cliente;
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
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        System.out.println("Atualizando cliente " + cliente.codigo );
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.codigo);
        try {
            clienteEncontrado.get();
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
