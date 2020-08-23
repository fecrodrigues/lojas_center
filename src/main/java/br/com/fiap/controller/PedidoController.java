package br.com.fiap.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.entity.Pedido;
import br.com.fiap.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService service;

	@GetMapping("/{id}")
	public List<Pedido> findByPedido(@PathParam("id") String idPedido){
		return service.findByPedido();
	}
	
	@GetMapping
	public List<Pedido> findByCliente(String idCliente){
		return service.findByCliente();
	}
	
	@PostMapping
	public Pedido addPedido(){
		return service.addPedido();
	}
	
	@PutMapping("/{id}")
	public Pedido updatePedido(@PathParam("id") String idPedido){
		return service.updatePedido();
	}
	
	@DeleteMapping("/{id}")
	public Boolean deletePedido(@PathParam("id") String idPedido){
		return service.deletePedido();
	}
}
