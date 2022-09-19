package com.letscode.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.configuration.security.AuthenticationFacade;
import com.letscode.ecommerce.configuration.security.IAuthenticationFacade;
import com.letscode.ecommerce.configuration.security.UserPrincipalDetails;
import com.letscode.ecommerce.configuration.security.UserServiceImpl;
import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.dao.PedidoDao;
import com.letscode.ecommerce.dao.ProdutoDao;
import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.Pedido;
import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoDao pedidoDao;

    @Autowired
    ProdutoDao produtoDao;

    @Autowired
    ClienteDao clienteDao;

    // @Autowired
    // UserServiceImpl userService;

    @Autowired
    IAuthenticationFacade authFacade;

    // Cliente cliente = findClienteLogado(); se colocar aqui apenas recebo
    // NullPointer -?-
    // Pedido pedidoCliente =
    // pedidoDao.findByEmailCliente(authFacade.getAuthentication().getPrincipal().toString());

    @Override
    public boolean adicionarProduto(long id) {
        try {
            // Acha o produto
            Optional<Produto> produto = produtoDao.findById(id);
            System.out.println("Achou o produto? - " + produto.isPresent());
            if (produto.isPresent()) {
                System.out.println("O produto existe e é: " + produto.get());
                // Salva o produto na lista e salva a lista
                Pedido pedido = findPedido();
                pedido.addProduto(produto.get());
                pedidoDao.save(pedido);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Problema ao encontrar e salvar o produto");
            return false;
        }
    }

    @Override
    public boolean fecharPedido() {
        try {
            Cliente cliente = findClienteLogado();
            Pedido pedido = cliente.getPedido();

            pedido.setIsOpen(false);

            pedidoDao.save(pedido);
            cliente.setPedido(pedido);
            clienteDao.save(cliente);
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // TODO: Futura implementação de mais de um pedido por cliente
    // @Override
    // public Pedido lerPedidoPorId(long id) {
    // // TODO Auto-generated method stub
    // return null;
    // }

    @Override
    public boolean novoPedido() {
        try {
            Cliente cliente = findClienteLogado();
            if (cliente.getPedido() == null) {
                Pedido pedido = new Pedido(cliente.getNome(), cliente.getEmail());
                cliente.setPedido(pedido);
                pedidoDao.save(cliente.getPedido());
                return true;
            } else {
                System.out.println("Cliente já possui pedido aberto.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exeption ao criar pedido: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removerPedido(long id) {
        // Se o id lançado for o mesmo id do pedido do cliente (Clientes só podem ter um
        // pedido aberto)
        try {
        Cliente cliente = findClienteLogado();
        Pedido pedidoCliente = cliente.getPedido();
        if (pedidoCliente.getId() == id) {
            pedidoDao.delete(pedidoCliente);
            cliente.setPedido(null);
            clienteDao.save(cliente);
            return true;
        } else {
            // Pedido não existe ou não pertence a pessoa logada (Logo lançar que não
            // encontrou)
            return false;
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
    }
    }

    @Override
    public boolean removerProduto(long id) {
        try {
            Cliente cliente = findClienteLogado();
            Pedido pedido = cliente.getPedido();
            List<Produto> listaProduto = pedido.getListaProduto().stream().filter(produto -> produto.getId() != id)
                    .toList();
            pedido.setListaProduto(listaProduto);
            cliente.setPedido(pedido);
            pedidoDao.save(pedido);
            clienteDao.save(cliente);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Pedido findPedido() {// Authentication authentication) {
        System.out.println("Iniciada a procura pelo pedido");
        Cliente cliente = findClienteLogado();
        System.out.println("Cliente encontrado: " + cliente.toString());
        Pedido pedido = cliente.getPedido();
        System.out.println("Pedido encontrado: " + pedido.toString());

        return pedido;

        // carregar o cliente
        // UserDetails user =
        // userService.loadUserByUsername(authentication.getPrincipal().toString());
    }

    public Cliente findClienteLogado() {
        String emailCliente = authFacade.getAuthentication().getPrincipal().toString();
        Cliente cliente = clienteDao.findByEmail(emailCliente);
        return cliente;
    }

}
