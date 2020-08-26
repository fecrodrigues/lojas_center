package br.com.fiap.controller;

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
import br.com.fiap.model.Response;
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
	public ResponseEntity<Response<Pedido>> findByPedido(@PathVariable("id") Long idPedido){
		Pedido pedido = service.findByPedido(idPedido);
		
		return new ResponseEntity<Response<Pedido>>(new Response<Pedido>("Pedido Encontrado", pedido), HttpStatus.OK);
	}
	
	@ApiOperation("Busca os pedidos relacionados a um cliente")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Retorna lista de pedidos de um cliente"),
		@ApiResponse(code = 404, message = "Informa que o cliente não possui nenhum pedido efetuado")
	})
	@GetMapping
	public ResponseEntity<Response<List<Pedido>>> findByCliente(long idCliente){
		List<Pedido> pedidos = service.findByCliente(idCliente);
		
		return new ResponseEntity<Response<List<Pedido>>>(new Response<List<Pedido>>("Pedidos encontrados", pedidos),HttpStatus.OK);
	}
	
	@ApiOperation("Adiciona um pedido novo")
	@ApiResponses( value = {
		@ApiResponse(code = 201, message = "Retorna o pedido cadastrado com sucesso")
	})
	@PostMapping
	public ResponseEntity<Response<Pedido>> addPedido(@RequestBody PedidoForm form, UriComponentsBuilder uriBuilder){
		Pedido pedidoCreated = service.addPedido(form);
		
		return new ResponseEntity<Response<Pedido>>(new Response<Pedido>("Produto adicionado", pedidoCreated), HttpStatus.CREATED);
	}
	
	@ApiOperation("Atualiza informações de um pedido")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Retorna o pedido atualizado com sucesso")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Response<Pedido>> updatePedido(@PathVariable("id") long idPedido, @RequestBody PedidoForm form){
		Pedido pedido = service.updatePedido(idPedido, form);
		
		return new ResponseEntity<Response<Pedido>>(new Response<Pedido>("Pedido atualizado com sucesso", pedido),HttpStatus.OK);
	}
	
	@ApiOperation("Remove o pedido informado")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Retorna o pedido encontrado")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Void>> deletePedido(@PathVariable("id") long idPedido){
		service.deletePedido(idPedido);
		
		return new ResponseEntity<Response<Void>>(new Response<Void>("Pedido removido com sucesso", null),HttpStatus.NO_CONTENT);
	}
}
