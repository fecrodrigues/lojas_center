package br.com.fiap.controller;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.fiap.model.ControleEstoqueProduto;
import br.com.fiap.model.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.entity.Produto;
import br.com.fiap.service.IProdutoService;

@Api(tags = "Produto")
@RestController
@RequestMapping("produto")
public class ProdutoController {
	@Autowired
	private IProdutoService produtoService;

	@ApiOperation("Retorna um produto especifico de acordo com o código do produto informado")
	@ApiResponses( value = {
		@ApiResponse(code = 200, message = "Retorna o produto encontrado"),
		@ApiResponse(code = 404, message = "Informa que o produto não foi encontrado")
	})
	@GetMapping("/{codigo}")
	public ResponseEntity<Response<Produto>> consultarProduto(@PathVariable("codigo") Long codigo) {

		try {
			Produto produto = produtoService.consultarProduto(codigo);
			return new ResponseEntity<Response<Produto>>(new Response<Produto>("Produto encontrado", produto), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Response<Produto>>(new Response<Produto>(e.getMessage(), null), HttpStatus.NOT_FOUND);
		}

	}

	@ApiOperation("Listar todos os produtos cadastrados")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Retorna a lista de produtos encontrado"),
			@ApiResponse(code = 404, message = "Informa que nenhum produto foi encontrado")
	})
	@GetMapping
	public ResponseEntity<Response<List<Produto>>> listarProdutos() {
		List<Produto> lista = produtoService.listarProdutos();
		if(lista.size() != 0) {
			return new ResponseEntity<Response<List<Produto>>>(new Response<List<Produto>>("Lista de produtos", lista), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response<List<Produto>>>(new Response<List<Produto>>("Nenhum produto cadastrado", lista), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation("Adicionar um novo produto")
	@ApiResponses( value = {
			@ApiResponse(code = 201, message = "Informa que o produto foi adicionado")
	})
	@PostMapping
	public ResponseEntity<Response<HttpHeaders>> adicionarProduto(@RequestBody Produto produto, UriComponentsBuilder builder) {
		Produto savedProduto = produtoService.adicionarProduto(produto);
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/produto/{codigo}").buildAndExpand(savedProduto.getCodigo()).toUri());
                return new ResponseEntity<Response<HttpHeaders>>(new Response<HttpHeaders>("Produto adicionado", headers), HttpStatus.CREATED);
	}

	@ApiOperation("Atualizar um produto especifico de acordo com o código do produto informado")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Retorna o produto que foi atualizado"),
			@ApiResponse(code = 404, message = "Informa que o produto não foi encontrado para atualização"),
			@ApiResponse(code = 400, message = "Código do produto não foi informado")
	})
	@PutMapping
	public ResponseEntity<Response<Produto>> atualizarProduto(@RequestBody Produto produto) {
		if(produto.getCodigo() != null) {
			try {
				produtoService.atualizarProduto(produto);
				return new ResponseEntity<Response<Produto>>( new Response<Produto>("Produto atualizado", produto), HttpStatus.OK );
			} catch(NoSuchElementException e) {
				return new ResponseEntity<Response<Produto>>( new Response<Produto>(e.getMessage(), null), HttpStatus.NOT_FOUND );
			}
		} else {
			return new ResponseEntity<Response<Produto>>(new Response<Produto>("Código não informado", null), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation("Excluir um produto especifico de acordo com o código do produto informado")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Retorna o produto que foi excluído"),
			@ApiResponse(code = 404, message = "Informa que o produto não foi encontrado para exclusão")
	})
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Response<String>> excluirProduto(@PathVariable("codigo") Long codigo) {
		try {
			produtoService.excluirProduto(codigo);
			return new ResponseEntity<Response<String>>(new Response<String>("Produto excluido", null), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Response<String>>(new Response<String>(e.getMessage(), null), HttpStatus.NOT_FOUND);
		}

	}

	@ApiOperation("Dar baixa no estoque do produto com a quantidade informada")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Baixa no estoque foi realizada com sucesso"),
			@ApiResponse(code = 404, message = "Produto não encontrado para dar baixa"),
			@ApiResponse(code = 400, message = "Produto não possui estoque suficiente para dar baixa")
	})
	@PatchMapping("estoque/dar-baixa")
	public ResponseEntity<Response<Produto>> darBaixaEstoque(@RequestBody ControleEstoqueProduto controleEstoque) {
		try {
			Produto produto = produtoService.darBaixaEstoque(controleEstoque.getCodigo(), controleEstoque.getQuantidade());
			return new ResponseEntity<Response<Produto>>(new Response<Produto>("Baixa no estoque realizada com sucesso!", produto), HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Response<Produto>>(new Response<Produto>(e.getMessage(), null), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Response<Produto>>(new Response<Produto>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation("Depositar no estoque do produto a quantidade informada")
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Deposito no estoque foi realizada com sucesso"),
			@ApiResponse(code = 404, message = "Produto não encontrado para depositar"),
			@ApiResponse(code = 400, message = "Quantidade informada é inválida")
	})
	@PatchMapping("estoque/depositar")
	public ResponseEntity<Response<Produto>> depositarEstoque(@RequestBody ControleEstoqueProduto controleEstoque) {
		try {
			Produto produto = produtoService.depositarEstoque(controleEstoque.getCodigo(), controleEstoque.getQuantidade());
			return new ResponseEntity<Response<Produto>>(new Response<Produto>("Deposito no estoque realizado com sucesso!", produto), HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Response<Produto>>(new Response<Produto>(e.getMessage(), null), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Response<Produto>>(new Response<Produto>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
		}
	}
} 