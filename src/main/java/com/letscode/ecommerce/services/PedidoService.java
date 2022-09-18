package com.letscode.ecommerce.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Pedido;

@Service
public interface PedidoService {

    List<Pedido> listarTodosPedidos();

    public boolean novoPedido(PedidoDto pedido);

    public boolean atualizarPedido(Pedido pedido);

    public boolean adicionarProduto(long id);

    public boolean removerProduto(ProdutoDto produtoDto);

    public boolean removerPedido(long id);

    public boolean fecharPedido(long id);

    public Pedido lerPedidoPorId(long id);

    public Pedido findPedido(); //Versão XGH de validação de cliente

}
