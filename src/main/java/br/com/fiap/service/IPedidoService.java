package br.com.fiap.service;

import java.util.List;

import br.com.fiap.entity.Pedido;
import br.com.fiap.model.PedidoForm;

public interface IPedidoService {
	public Pedido findByPedido(long idPedido);
	public List<Pedido> findByCliente(long idCliente);
	public Pedido addPedido(PedidoForm form);
	public Pedido updatePedido(long idPedido, PedidoForm form);
	public void deletePedido(long idPedido);
}
