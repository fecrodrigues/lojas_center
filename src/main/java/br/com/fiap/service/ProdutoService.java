package br.com.fiap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.fiap.entity.Produto;
import br.com.fiap.repository.ProdutoRepository;

@Service
public class ProdutoService implements IProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override	
	@Cacheable(value= "produtoCache", key= "#codigo")
	public Produto consultarProduto(Long codigo) throws NoSuchElementException {
		System.out.println("getProdutoById()");
		try {
			return produtoRepository.findById(codigo).get();
		} catch(NoSuchElementException e) {
			throw new NoSuchElementException("Produto não encotrado");
		}
	}

	@Override
	@Cacheable(value= "listaProdutosCache", unless= "#result.size() == 0")
	public List<Produto> listarProdutos(){
		System.out.println("listando produtos");
		List<Produto> lista = new ArrayList<>();
		produtoRepository.findAll().forEach(e -> lista.add(e));
		return lista;
	}

	@Override	
	@Caching(
		put= { @CachePut(value= "produtoCache", key= "#produto.codigo") },
		evict= { @CacheEvict(value= "listaProdutosCache", allEntries= true) }
	)
	public Produto adicionarProduto(Produto produto){
		System.out.println("Adicionando produto");
		return produtoRepository.save(produto);
	}

	@Override	
	@Caching(
		put= { @CachePut(value= "produtoCache", key= "#produto.codigo") },
		evict= { @CacheEvict(value= "listaProdutosCache", allEntries= true) }
	)
	public Produto atualizarProduto(Produto produto) throws NoSuchElementException {
		System.out.println("Atualizando produto " + produto.codigo );
		Optional<Produto> produtoEncontrado = produtoRepository.findById(produto.codigo);
		try {
			produtoEncontrado.get();
			return produtoRepository.save(produto);
		} catch(NoSuchElementException e) {
			throw new NoSuchElementException("Produto não encontrado para atualização");
		}
	}

	@Override	
	@Caching(
		evict= {
			@CacheEvict(value= "produtoCache", key= "#codigo"),
			@CacheEvict(value= "listaProdutosCache", allEntries= true)
		}
	)
	public void excluirProduto(Long codigo) throws NoSuchElementException {
		System.out.println("Excluindo produto " + codigo);
		try {
			produtoRepository.delete(produtoRepository.findById(codigo).get());
		} catch(NoSuchElementException e) {
			throw new NoSuchElementException("Produto não encotrado");
		}

	}

	@Override
	@Caching(
		put = { @CachePut(value="produtoCache", key="#codigo") },
		evict = { @CacheEvict(value="listaProdutosCache", allEntries = true) }
	)
	public Produto darBaixaEstoque(Long codigo, Integer quantidade) throws Exception {
		try {
			if(quantidade > 0) {
				Produto produto = produtoRepository.findById(codigo).get();
				produto.quantidade -= quantidade;

				if(produto.quantidade < 0) {
					throw new Exception("Produto não possui a quantidade desejada no estoque");
				} else {
					produtoRepository.save(produto);
					return produto;
				}
			} else {
				throw new Exception("Quantidade informada é inválida");
			}
		} catch(NoSuchElementException e) {
			throw  new NoSuchElementException("Produto não encontrado");
		}
	}

	@Override
	@Caching(
		put = { @CachePut(value="produtoCache", key="#codigo") },
		evict = { @CacheEvict(value="listaProdutosCache", allEntries = true) }
	)
	public Produto depositarEstoque(Long codigo, Integer quantidade) throws Exception {
		try {

			if(quantidade > 0) {
				Produto produto = produtoRepository.findById(codigo).get();
				produto.quantidade += quantidade;

				produtoRepository.save(produto);
				return produto;
			} else {
				throw new Exception("Quantidade informada é inválida");
			}

		} catch(NoSuchElementException e) {
			throw  new NoSuchElementException("Produto não encontrado");
		}
	}
} 