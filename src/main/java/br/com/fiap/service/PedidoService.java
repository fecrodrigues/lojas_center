package br.com.fiap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.Pedido;
import br.com.fiap.exceptions.NotCreatedPedidoException;
import br.com.fiap.exceptions.NotFoundPedidoException;
import br.com.fiap.model.PedidoForm;
import br.com.fiap.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	private static final String NOT_FOUND_ERROR_MSG_PEDIDO = "Nenhum pedido não foi encontrado";
	private static final String PEDIDO_CREATED_ERROR_MSG = "Erro ao criar o pedido. Favor tente novamente mais tarde";
	
	@Cacheable(value = "cacheblePedidoById" ,key = "#idPedido" )
	public Pedido findByPedido(long idPedido){
		Pedido pedido = repository.findById(idPedido)
				.orElseGet(null);
		
		if(pedido == null) {
			throw new NotFoundPedidoException(NOT_FOUND_ERROR_MSG_PEDIDO);
		}
		
		return pedido;
	}
	
	@Cacheable(value = "cacheblePedidoByClient" ,key = "#idCliente" )
	public List<Pedido> findByCliente(long idCliente){
		List<Pedido> pedidos = repository.findByIdCliente(idCliente);
		
		if(pedidos == null || pedidos.isEmpty()) {
			throw new NotFoundPedidoException(NOT_FOUND_ERROR_MSG_PEDIDO);
		}
		
		return pedidos;
	}
	
	@Caching(put= { @CachePut(value= "cacheblePedido", key= "#pedido.idPedido") })
	public Pedido addPedido(Pedido pedido){
		Pedido pedidoCreated = repository.save(pedido);
		
		if(pedidoCreated == null) {
			throw new NotCreatedPedidoException(PEDIDO_CREATED_ERROR_MSG);
		}
		
		return pedidoCreated;
	}
	
	@Transactional
	@Caching(put= { @CachePut(value= "cacheblePedido", key= "#pedido.idPedido") })
	public Pedido updatePedido(long idPedido, PedidoForm form){
		Pedido pedido = findByPedido(idPedido);
		
		pedido.setIdCliente(form.getIdCliente());
		pedido.setDataCompra(form.getDataCompra());
		
		return findByPedido(idPedido);
	}
	
	@Caching(
			evict= { 
				@CacheEvict(value= "cacheblePedidoById", key= "#idPedido"),
				@CacheEvict(value= "cacheblePedido", key= "#idCliente")
			}
		)
	public void deletePedido(long idPedido){
		repository.deleteById(idPedido);
	}
}
