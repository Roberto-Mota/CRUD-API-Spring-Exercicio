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

    //Pedido pedidoCliente = pedidoDao.findByEmailCliente(authFacade.getAuthentication().getPrincipal().toString());

    @Override
    public List<Pedido> listarTodosPedidos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean adicionarProduto(long id) {
        // TODO Procurar o produto no banco de dados e inseri-lo na lista do pedido
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
    public boolean atualizarPedido(Pedido pedido) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean fecharPedido(long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Pedido lerPedidoPorId(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean novoPedido(PedidoDto pedidoDto) {
        try {
            Pedido pedido = new Pedido(pedidoDto.getNomeCliente(), pedidoDto.getEmailCliente());
            String emailCliente = authFacade.getAuthentication().getPrincipal().toString();
            Cliente cliente = clienteDao.findByEmail(emailCliente);
            cliente.setPedido(pedido);
            pedidoDao.save(cliente.getPedido());
            return true;
        } catch (Exception e) {
            System.out.println("Exeption ao encontrar pedido: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removerPedido(long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removerProduto(ProdutoDto produtoDto) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Pedido findPedido() {// Authentication authentication) {
        System.out.println("Iniciada a procura pelo pedido");
        String emailCliente = authFacade.getAuthentication().getPrincipal().toString();
        System.out.println("Cliente o qual terá o pedido procurado: " + emailCliente);
        //Pedido pedido = pedidoDao.findByemailCliente(emailCliente);
        Cliente cliente = clienteDao.findByEmail(emailCliente);
        System.out.println("Cliente encontrado: " + cliente.toString());
        Pedido pedido = cliente.getPedido();
        System.out.println("Pedido encontrado: " + pedido.toString());

        return pedido;

        // carregar o cliente
        // UserDetails user =
        // userService.loadUserByUsername(authentication.getPrincipal().toString());
    }

    /*
     * Preciso checar se o o usuario é dono da lista
     * Pra isso eu posso usar o (principal.toString()) e contains authenticated=true
     * no toString
     * e se o user é == au do dono da lista, mas parece ser meio gambiarra (se eu
     * não achar outra solução vai ser essa msm)
     */

}
