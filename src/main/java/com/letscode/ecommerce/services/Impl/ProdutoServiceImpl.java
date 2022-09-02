package com.letscode.ecommerce.services.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.letscode.ecommerce.dao.ProdutoDao;
import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.services.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoDao produtoDao;

    @Override
    public String testa() {
        // LocalDateTime horas = new LocalDateTime.now();
        return LocalDateTime.now().toString();
    }

    @Override
    public List<Produto> listarTodosProdutos() {
        return produtoDao.findAll();
    }

    @Override
    public boolean novoProduto(ProdutoDto produto) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean atualizarProduto(Produto produto) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removerProduto(long id) {
        // TODO Auto-generated method stub
        return false;
    }

    // public String testa() {
    // //LocalDateTime horas = new LocalDateTime.now();
    // return LocalDateTime.now().toString();
    // }

}
