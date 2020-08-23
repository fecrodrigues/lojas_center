package br.com.fiap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.entity.Pedido;
import br.com.fiap.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	
	public Pedido findByPedido(String idPedido){
		return repository.findById(Long.parseLong(idPedido));
	}
	
	public List<Pedido> findByCliente(String idCliente){
		return repository.findByCliente();
	}
	
	public Pedido addPedido(){
		
		return repository.save(entity);
	}
	
	public Pedido updatePedido(String idPedido){
		return repository.updatePedido();
	}
	
	public Boolean deletePedido(String idPedido){
		return repository.deletePedido();
	}
}
