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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedido")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService service;

	@ApiOperation("Busca o pedido informado")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Retorna o pedido encontrado"),
		@ApiResponse(code = 404, message = "Informa que o pedido não foi encontrado")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findByPedido(@PathVariable("id") Long idPedido){
		return ResponseEntity.ok(service.findByPedido(idPedido));
	}
	
	@ApiOperation("Busca os pedidos relacionados a um cliente")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Retorna lista de pedidos de um cliente"),
		@ApiResponse(code = 404, message = "Informa que o cliente não possui nenhum pedido efetuado")
	})
	@GetMapping
	public ResponseEntity<List<Pedido>> findByCliente(long idCliente){
		return ResponseEntity.ok(service.findByCliente(idCliente));
	}
	
	@ApiOperation("Adiciona um pedido novo")
	@ApiResponses( value = {
		@ApiResponse(code = 201, message = "Retorna o pedido cadastrado com sucesso")
	})
	@PostMapping
	public ResponseEntity<?> addPedido(@RequestBody PedidoForm form, UriComponentsBuilder uriBuilder){
		Pedido pedidoCreated = service.addPedido(form);
		URI uri = uriBuilder.path("/pedidos/{idPedido}").buildAndExpand(pedidoCreated.getCodigo()).toUri();
		
		return ResponseEntity.ok("");
	}
	
	@ApiOperation("Atualiza informações de um pedido")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Retorna o pedido atualizado com sucesso")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> updatePedido(@PathVariable("id") long idPedido, @RequestBody PedidoForm form){
		return ResponseEntity.ok(service.updatePedido(idPedido, form));
	}
	
	@ApiOperation("Remove o pedido informado")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Retorna o pedido encontrado")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePedido(@PathVariable("id") long idPedido){
		service.deletePedido(idPedido);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
