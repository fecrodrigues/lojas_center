package br.com.fiap.controller;

import br.com.fiap.entity.Cliente;
import br.com.fiap.model.Response;
import br.com.fiap.service.IClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;

@Api(tags = "Cliente")
@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @ApiOperation("Retorna um ciente especifico de acordo com o código informado")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o cliente encontrado"),
            @ApiResponse(code = 404, message = "Informa que o cliente não foi encontrado")
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<Response<Cliente>> consultarCliente(@PathVariable("codigo") Long codigo) {

        try {
            Cliente cliente = clienteService.consultarCliente(codigo);
            return new ResponseEntity<Response<Cliente>>(new Response<Cliente>("Cliente encontrado", cliente), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Response<Cliente>>(new Response<Cliente>(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation("Listar todos os clientes cadastrados")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes encontrada"),
            @ApiResponse(code = 404, message = "Informa que nenhum cliente foi encontrado")
    })
    @GetMapping
    public ResponseEntity<Response<List<Cliente>>> listarClientes() {
        List<Cliente> lista = clienteService.listarClientes();
        if(lista.size() != 0) {
            return new ResponseEntity<Response<List<Cliente>>>(new Response<List<Cliente>>("Lista de clientes", lista), HttpStatus.OK);
        } else {
            return new ResponseEntity<Response<List<Cliente>>>(new Response<List<Cliente>>("Nenhum cliente cadastrado", lista), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Adicionar um novo cliente")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Informa que o cliente foi adicionado")
    })
    @PostMapping
    public ResponseEntity<Response<HttpHeaders>> adicionarCliente(@RequestBody Cliente cliente, UriComponentsBuilder builder) {
        Cliente savedCliente = clienteService.adicionarCliente(cliente);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/cliente/{codigo}").buildAndExpand(savedCliente.getCodigo()).toUri());
        return new ResponseEntity<Response<HttpHeaders>>(new Response<HttpHeaders>("Cliente adicionado", headers), HttpStatus.CREATED);
    }

    @ApiOperation("Atualizar um cliente especifico de acordo com o código informado")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o cliente que foi atualizado"),
            @ApiResponse(code = 404, message = "Informa que o cliente não foi encontrado para atualização"),
            @ApiResponse(code = 400, message = "Código do cliente não foi informado")
    })
    @PutMapping
    public ResponseEntity<Response<Cliente>> atualizarCliente(@RequestBody Cliente cliente) {
        if(cliente.getCodigo() != null) {
            try {
                clienteService.atualizarCliente(cliente);
                return new ResponseEntity<Response<Cliente>>( new Response<Cliente>("Cliente atualizado", cliente), HttpStatus.OK );
            } catch(NoSuchElementException e) {
                return new ResponseEntity<Response<Cliente>>( new Response<Cliente>(e.getMessage(), null), HttpStatus.NOT_FOUND );
            }
        } else {
            return new ResponseEntity<Response<Cliente>>(new Response<Cliente>("Código não informado", null), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Excluir um cliente especifico de acordo com o código informado")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o cliente que foi excluído"),
            @ApiResponse(code = 404, message = "Informa que o cliente não foi encontrado para exclusão")
    })
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Response<String>> excluirCliente(@PathVariable("codigo") Long codigo) {
        try {
            clienteService.excluirCliente(codigo);
            return new ResponseEntity<Response<String>>(new Response<String>("Cliente excluído", null), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Response<String>>(new Response<String>(e.getMessage(), null), HttpStatus.NOT_FOUND);
        }

    }

}
