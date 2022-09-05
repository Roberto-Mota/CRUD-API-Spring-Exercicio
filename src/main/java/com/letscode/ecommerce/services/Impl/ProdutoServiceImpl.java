package com.letscode.ecommerce.services.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dao.ProdutoDao;
import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.services.ProdutoService;

@Service //Não esquecer da anotação!
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
        return produtoDao.findAll(); //Método padrão do JPA
    }

    @Override
    public boolean novoProduto(ProdutoDto produtoDto) {
        try {
            Produto produto = new Produto(produtoDto.getNome(), produtoDto.getPreco(), produtoDto.getDescricao());
            produtoDao.save(produto);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean atualizarProduto(Produto produto) {
        try {
            produtoDao.save(produto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removerProduto(long id) {
        try {
            produtoDao.deleteById(id); //DELETE FROM CLIENTE WHERE ID - {}
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // public String testa() {
    // //LocalDateTime horas = new LocalDateTime.now();
    // return LocalDateTime.now().toString();
    // }

}
