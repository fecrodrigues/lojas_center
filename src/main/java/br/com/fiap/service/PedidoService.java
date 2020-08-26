package br.com.fiap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.Produto;
import br.com.fiap.exceptions.DarBaixaException;
import br.com.fiap.exceptions.NotCreatedPedidoException;
import br.com.fiap.exceptions.NotFoundPedidoException;
import br.com.fiap.model.PedidoForm;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.repository.PedidoRepository;
import br.com.fiap.repository.ProdutoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	private static final String NOT_FOUND_ERROR_MSG_PEDIDO = "Nenhum pedido não foi encontrado";
	private static final String PEDIDO_CREATED_ERROR_MSG = "Erro ao criar o pedido. Favor tente novamente mais tarde";
	
	@Cacheable(value = "cacheblePedidoById" ,key = "#idPedido" )
	public Pedido findByPedido(long idPedido){
		Pedido pedido = repository.findById(idPedido)
				.orElse(null);
		
		if(pedido == null) {
			throw new NotFoundPedidoException(NOT_FOUND_ERROR_MSG_PEDIDO);
		}
		
		return pedido;
	}
	
	@Cacheable(value = "cacheblePedidoByClient" ,key = "#idCliente" )
	public List<Pedido> findByCliente(long idCliente){
		List<Pedido> pedidos = repository.findByClienteCodigo(idCliente);
		
		if(pedidos == null || pedidos.isEmpty()) {
			throw new NotFoundPedidoException(NOT_FOUND_ERROR_MSG_PEDIDO);
		}
		
		return pedidos;
	}
	
	@Caching(put= { @CachePut(value= "cacheblePedido", key= "#result.codigo") })
	public Pedido addPedido(PedidoForm form){
		List<Produto> produtos = new ArrayList<>();
		Cliente newCliente = clienteRepository.findById(form.getIdCliente())
				.orElse(null);

		form.getProdutos().forEach(produtoForm -> {
			produtos.add(produtoRepository.findById(produtoForm.getCodigo()).orElse(null));
		});
		
		Pedido pedidoCreated = repository.save(
				new Pedido(newCliente, produtos, form.getDataCompra())
		);
		
		if(pedidoCreated == null) {
			throw new NotCreatedPedidoException(PEDIDO_CREATED_ERROR_MSG);
		}
		
		return pedidoCreated;
	}
	
	@Transactional
	@Caching(put= { @CachePut(value= "cacheblePedido", key= "#result.codigo") })
	public Pedido updatePedido(long idPedido, PedidoForm form){
		Pedido pedido = findByPedido(idPedido);
		Cliente newCliente = clienteRepository.findById(form.getIdCliente())
				.orElse(null);
		
		pedido.setCliente(newCliente);
		pedido.setDataCompra(form.getDataCompra());
		
		return findByPedido(idPedido);
	}
	
	@Caching(
			evict= { 
				@CacheEvict(value= "cacheblePedidoById", key= "#idCliente"),
				@CacheEvict(value= "cacheblePedido", key= "#idPedido")
			}
		)
	public void deletePedido(long idPedido){
		Pedido pedido = repository.findById(idPedido).get();
		long idCliente = pedido.getCliente().getCodigo();
		
		pedido.getProdutos().forEach(produto ->{
			try {
				produtoService.darBaixaEstoque(produto.getCodigo(), produto.getQuantidade());
			} catch (Exception e) {
				throw new DarBaixaException("Erro ao dar baixa nos produtos:{nome:"+produto.getNome()+",quantidade:"+produto.getQuantidade());
			}
		});
		
		repository.deleteById(idPedido);
	}
}
