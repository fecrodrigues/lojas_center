package br.com.fiap.service;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.fiap.entity.Produto;

public interface IProdutoService {
     List<Produto> listarProdutos();
     Produto consultarProduto(Long codigo) throws NoSuchElementException;
     Produto adicionarProduto(Produto produto);
     Produto atualizarProduto(Produto produto) throws NoSuchElementException;
     void excluirProduto(Long id) throws NoSuchElementException;
     Produto darBaixaEstoque(Long codigo, Integer quantidade) throws Exception;
     Produto depositarEstoque(Long codigo, Integer quantidade) throws Exception;
} 
