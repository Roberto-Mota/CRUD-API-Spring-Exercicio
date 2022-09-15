package com.letscode.ecommerce.dao;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.Pedido;

public interface PedidoDao extends JpaRepository<Pedido, Long> {

    //List<Pedido> findAllByIdOrEmail(long id, String email); 
    Pedido findBynomeCliente(String nomeCliente);
}
