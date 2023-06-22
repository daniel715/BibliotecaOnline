package com.daniel.bibliotecaonline.dao;

import com.daniel.bibliotecaonline.dto.Pedido;

import java.util.Optional;

public interface IPedidoRepository {
//    public Iterable<Pedido> findAll();

    Pedido findById(String id);

    Pedido save(Pedido pedido);

    Pedido updatePedido(String pedidoId, Pedido autor);

    void delete(String pedidoId);

    Iterable<Pedido> findAll();
}
