package com.letscode.ecommerce.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Pedido;

@Service
public interface PedidoService {

    public boolean novoPedido();

    public boolean adicionarProduto(long id);

    public boolean removerProduto(long id);

    public boolean removerPedido(long id);

    public boolean fecharPedido();

    // public Pedido lerPedidoPorId(long id); TODO: Futura implementação de mais de um pedido por cliente

    public Pedido findPedido(); //Versão XGH de validação de cliente

}
