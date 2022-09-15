package com.letscode.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dao.PedidoDao;
import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Pedido;
import com.letscode.ecommerce.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoDao pedidoDao;

    @Override
    public List<Pedido> listarTodosPedidos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean novoPedido(PedidoDto pedidoDto) {
        try {
            Pedido pedido = new Pedido(pedidoDto.getNomeCliente(), pedidoDto.getEmailCliente());
            pedidoDao.save(pedido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean atualizarPedido(Pedido pedido) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean adicionarProduto(ProdutoDto produtoDto) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removerProduto(ProdutoDto produtoDto) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removerPedido(long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Pedido lerPedidoPorId(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
