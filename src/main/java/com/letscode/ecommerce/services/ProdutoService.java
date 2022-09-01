package com.letscode.ecommerce.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.letscode.ecommerce.models.Produto;

@Service
public class ProdutoService {
//     boolean createCliente
//     boolean readCliente
//     boolean updateCliente
//     boolean deleteCliente
//so testar o pq do erro:
public String testa() {
    //LocalDateTime horas = new LocalDateTime.now();
    return LocalDateTime.now().toString();
 }

public static List<Produto> listarTodosProdutos() {
    return null;
}

public boolean removerProduto(long id) {
    return false;
}

public boolean atualizarProduto() {
    return false;
}
}
