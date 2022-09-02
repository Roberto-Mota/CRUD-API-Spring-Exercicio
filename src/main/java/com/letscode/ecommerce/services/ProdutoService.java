package com.letscode.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Produto;

@Service
public interface ProdutoService {

public String testa();

List<Produto> listarTodosProdutos();

public boolean novoProduto(ProdutoDto produto);

public boolean atualizarProduto(Produto produto);

public boolean removerProduto(long id);

}
