package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dao.IPedidoRepository;
import com.daniel.bibliotecaonline.dto.Categoria;
import com.daniel.bibliotecaonline.dto.Libro;
import com.daniel.bibliotecaonline.dto.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
class PedidoRepository implements IPedidoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PedidoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Iterable<Pedido> findAll() {
        return jdbcTemplate.query("call Pedido_list()", this::mapRowToPedido);
    }

    @Override
    public Pedido findById(String id) {
        List<Pedido> results = jdbcTemplate.query("call Pedido_find_by_id(?)", this::mapRowToPedido, id);
        return results.size() == 0 ?
                null :
                results.get(0);
    }

    @Override
    public Pedido save(Pedido pedido) {
        jdbcTemplate.update("call Pedido_save(?,?,?,?,?,?,?,?,?,?)",
                pedido.getIdPedido(),
                pedido.getNombreComprador(),
                pedido.getTelefonoComprador(),
                pedido.getDireccionEntrega(),
                pedido.getDireccionIp(),
                pedido.getAppPago(),
                pedido.getFechaPedido(),
                pedido.getValorCompra(),
                pedido.getRespuestaPago(),
                pedido.getStatus()
        );
        return pedido;
    }

    @Override
    public Pedido updatePedido(String pedidoId, Pedido pedido) {
        Pedido pedidoToSend = this.findById(pedidoId);
        if (pedidoToSend == null) return null;
        else {
            if (pedido.getNombreComprador() != null) {
                pedidoToSend.setNombreComprador(pedido.getNombreComprador());
            }
            if (pedido.getTelefonoComprador() != null) {
                pedidoToSend.setTelefonoComprador(pedido.getTelefonoComprador());
            }
            if (pedido.getDireccionEntrega() != null) {
                pedidoToSend.setDireccionEntrega(pedido.getDireccionEntrega());
            }
            if (pedido.getDireccionIp() != null) {
                pedidoToSend.setDireccionIp(pedido.getDireccionIp());
            }
            if (pedido.getAppPago() != null) {
                pedidoToSend.setAppPago(pedido.getAppPago());
            }
            if (pedido.getFechaPedido() != null) {
                pedidoToSend.setFechaPedido(pedido.getFechaPedido());
            }
            if (pedido.getValorCompra() != null) {
                pedidoToSend.setValorCompra(pedido.getValorCompra());
            }
            if (pedido.getRespuestaPago() != null) {
                pedidoToSend.setRespuestaPago(pedido.getRespuestaPago());
            }
            if (pedido.getStatus() != null) {
                pedidoToSend.setStatus(pedido.getStatus());
            }
            String sqlquery = "call Pedido_update( ?, ?, ?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sqlquery,
                    pedidoToSend.getIdPedido(),
                    pedidoToSend.getNombreComprador(),
                    pedidoToSend.getTelefonoComprador(),
                    pedidoToSend.getDireccionEntrega(),
                    pedidoToSend.getDireccionIp(),
                    pedidoToSend.getAppPago(),
                    pedidoToSend.getFechaPedido(),
                    pedidoToSend.getValorCompra(),
                    pedidoToSend.getRespuestaPago(),
                    pedidoToSend.getStatus()
            );
            return pedidoToSend;
        }
    }

    @Override
    public void delete(String pedidoId) {
        String sqlquery = "call Pedido_delete(?);";
        jdbcTemplate.update(sqlquery, pedidoId);
    }

    private Pedido mapRowToPedido(ResultSet row, int i) throws SQLException {
        return new Pedido(
                row.getString("id_pedido"),
                row.getString("nombre_comprador"),
                row.getString("telefono_comprador"),
                row.getString("direccion_entrega"),
                row.getString("direccion_ip"),
                row.getString("app_pago"),
                row.getDate("fecha_pedido"),
                row.getFloat("valor_compra"),
                row.getString("respuesta_pago"),
                row.getString("status")
        );
    }
}
