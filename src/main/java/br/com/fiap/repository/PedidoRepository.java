package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.fiap.entity.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	
	public List<Pedido> findByClienteCodigo(long idCliente);
}