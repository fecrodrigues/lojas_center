package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.entity.Pedido;
import br.com.fiap.model.PedidoForm;
import br.com.fiap.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService service;

	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findByPedido(@PathVariable("id") Long idPedido){
		return ResponseEntity.ok(service.findByPedido(idPedido));
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findByCliente(long idCliente){
		return ResponseEntity.ok(service.findByCliente(idCliente));
	}
	
	@PostMapping
	public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido, UriComponentsBuilder uriBuilder){
		Pedido pedidoCreated = service.addPedido(pedido);
		URI uri = uriBuilder.path("/pedidos/{idCliente}").buildAndExpand(pedidoCreated.getCliente().getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(pedidoCreated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> updatePedido(@PathVariable("id") long idPedido, @RequestBody PedidoForm form){
		return ResponseEntity.ok(service.updatePedido(idPedido, form));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePedido(@PathVariable("id") long idPedido){
		service.deletePedido(idPedido);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
