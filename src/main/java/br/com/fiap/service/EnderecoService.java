package br.com.fiap.service;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Endereco;
import br.com.fiap.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService implements IEnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Override
    public Endereco salvarClienteEndereco(Cliente cliente, Endereco endereco) {
        endereco.cliente = cliente;
        if(cliente.codigo != null) endereco.codigo_cliente = cliente.codigo;

        return enderecoRepository.save(endereco);
    }
}
